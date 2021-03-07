package com.ben.myshoppinglist.model


import com.google.gson.annotations.SerializedName

data class UserResponse(
    val created_at: String,
    val email: String,
    val id: Int,
    val password: String,
    val updated_at: String,
    val uuid: String
)