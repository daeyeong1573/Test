package com.example.test.sign

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import com.example.test.MainActivity
import com.example.test.R
import com.example.test.databinding.ActivityLoginBinding
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class LoginActivity : AppCompatActivity() {

    val lbinding by lazy { ActivityLoginBinding.inflate(layoutInflater) }
    val TAG: String = "로그"

    val PREFERENCE = "template.android.hyogeuns"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(lbinding.root)

        SharedPref.openSharedPrep(this)

        lbinding.btnLogin.setOnClickListener {
            Client.api.login(Users(id = lbinding.email.text.toString(),pw = lbinding.password.text.toString())).enqueue(object : Callback<UserData>{
                override fun onResponse(call: Call<UserData>, response: Response<UserData>) {
                    when(response!!.code()){
                        200 -> {
                            val pref = getSharedPreferences(PREFERENCE, MODE_PRIVATE)
                            val editor = pref.edit()
                            editor.putString("username", lbinding.email.text.toString())
                            editor.commit()
                            finish()
                            Log.d(TAG,"성공")
                            Toast.makeText(this@LoginActivity,"로그인 성공!",Toast.LENGTH_SHORT).show()
                            startActivity(Intent(this@LoginActivity,MainActivity::class.java))
                        }
                        405 -> Toast.makeText(this@LoginActivity, "로그인 실패 : 아이디나 비번이 올바르지 않습니다", Toast.LENGTH_LONG).show()
                        500 -> Toast.makeText(this@LoginActivity, "로그인 실패 : 서버 오류", Toast.LENGTH_LONG).show()
                    }
                }

                override fun onFailure(call: Call<UserData>, t: Throwable) {
                    Log.d(TAG,"onFailure() - ${t}")
                }

            })
        }


    }
}