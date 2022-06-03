package com.app.postdemo.models

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class PostComment(
    val postId: Int,
    @PrimaryKey(autoGenerate = false)
    val id: Int,
    val name: String,
    val email: String,
    val body: String
)