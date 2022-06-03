package com.app.postdemo.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.app.postdemo.models.FavoritesPost
import com.app.postdemo.models.Post
import com.app.postdemo.models.PostComment

@Database(entities = [Post::class, PostComment::class, FavoritesPost::class], version = 1, exportSchema = false)
abstract class PostDB : RoomDatabase() {
    abstract fun getPostDAO() : PostDAO
}