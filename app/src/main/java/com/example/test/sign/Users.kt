package com.example.test.sign

import com.google.gson.annotations.SerializedName

data class Users(
    @SerializedName("ID")
    val id :String,
    @SerializedName("Pw")
    val pw :String
)
