package com.example.test

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.example.test.databinding.ActivityMainBinding
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity() {

    val binding by lazy { ActivityMainBinding.inflate(layoutInflater)}
    val TAG: String = "로그"
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        binding.user.setOnClickListener {
            Builder.api.getUserInfo().enqueue(object : Callback<UserInfo>{
                override fun onResponse(call: Call<UserInfo>, response: Response<UserInfo>) {
                    val user = response.body()!!
                    binding.user.text = user?.userId
                    Log.d(TAG,"onResponse() : ${user?.userId}")
                }

                override fun onFailure(call: Call<UserInfo>, t: Throwable) {
                    TODO("Not yet implemented")
                }

            })
        }
    }
}