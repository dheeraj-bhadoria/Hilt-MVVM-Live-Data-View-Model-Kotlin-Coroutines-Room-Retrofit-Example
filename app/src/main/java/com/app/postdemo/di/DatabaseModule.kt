package com.app.postdemo.di

import android.content.Context
import androidx.room.Room
import com.app.postdemo.db.PostDB
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
class DatabaseModule {

    @Singleton
    @Provides
    fun provideFakerDB(@ApplicationContext context : Context) : PostDB {
        return Room.databaseBuilder(context, PostDB::class.java, "PostDB").build()
    }
}