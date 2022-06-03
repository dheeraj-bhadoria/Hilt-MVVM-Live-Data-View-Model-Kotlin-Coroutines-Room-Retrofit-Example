package com.app.postdemo.activity

import android.content.Context
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Button
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.app.postdemo.Interface.ItemListener
import com.app.postdemo.R
import com.app.postdemo.adapter.PostAdapter
import com.app.postdemo.adapter.PostCommentAdapter
import com.app.postdemo.models.FavoritesPost
import com.app.postdemo.models.Post
import com.app.postdemo.utils.AppUtils
import com.app.postdemo.viewmodels.FavoritesPostViewModel
import com.app.postdemo.viewmodels.PostCommentsViewModel
import com.app.postdemo.viewmodels.PostViewModel
import dagger.hilt.android.AndroidEntryPoint
import com.google.gson.Gson




@AndroidEntryPoint
class PostComents: AppCompatActivity(), ItemListener {

    lateinit var postCommentViewModel: PostCommentsViewModel
    lateinit var favoritesPostViewModel: FavoritesPostViewModel

    val postsCommentsRV: RecyclerView
        get() = findViewById(R.id.postsCommentsRV)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_post_detail)

        supportActionBar?.title = "Comments"
        supportActionBar?.setDisplayHomeAsUpEnabled(true)


        postCommentViewModel = ViewModelProvider(this).get(PostCommentsViewModel::class.java)
        favoritesPostViewModel = ViewModelProvider(this).get(FavoritesPostViewModel::class.java)

        postCommentViewModel.getPostComments(1).observe(this@PostComents, Observer {
            postsCommentsRV.layoutManager = LinearLayoutManager(this@PostComents)
            postsCommentsRV.adapter = PostCommentAdapter(it,this@PostComents,  this!!)
        })

        if(AppUtils.isInterConnectionIsAvailable(this@PostComents)){

        }else{
            AppUtils.showErrorDialog(this@PostComents, resources.getString(R.string.internetNotAvailableStr))
        }
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if(item.itemId == android.R.id.home){
            finish()
        }else if(item.itemId == R.id.menu_item){
            val gson = Gson()
            val postObject: FavoritesPost =
                gson.fromJson(intent.getStringExtra("Post"), FavoritesPost::class.java)
            favoritesPostViewModel.saveFavoritesPost(postObject)
            Toast.makeText(this@PostComents, "Item added to favorite list", Toast.LENGTH_LONG).show()
        }else {

        }
        return super.onOptionsItemSelected(item)

    }

    override fun itemClicked(position: Int, context: Context) {
    }


    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        getMenuInflater().inflate(R.menu.menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

}