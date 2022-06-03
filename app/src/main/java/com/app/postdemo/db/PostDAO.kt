package com.app.postdemo.db

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.app.postdemo.models.FavoritesPost
import com.app.postdemo.models.Post
import com.app.postdemo.models.PostComment

@Dao
interface PostDAO {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addPosts(products : List<Post>)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addPostComment(postComment: List<PostComment>)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addFavoritesPost(post: FavoritesPost)

    @Query("SELECT * FROM Post")
    suspend fun getPosts() : List<Post>

    @Query("SELECT * FROM PostComment")
    suspend fun getPostComment(): List<PostComment>

    @Query("SELECT * FROM FavoritesPost")
    suspend fun getFavoritesPost(): List<FavoritesPost>
}