package com.app.postdemo.models

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Post(
    val userId: Int,
    @PrimaryKey(autoGenerate = false)
    val id: Int,
    val title: String,
    val body: String
)