package com.app.postdemo.fragment

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.app.postdemo.Interface.ItemListener
import com.app.postdemo.activity.PostComents
import com.app.postdemo.adapter.FavoritePostAdapter
import com.app.postdemo.adapter.PostAdapter
import com.app.postdemo.models.FavoritesPost
import com.app.postdemo.models.Post
import com.app.postdemo.viewmodels.FavoritesPostViewModel
import com.app.postdemo.viewmodels.PostViewModel
import com.example.postdemo.R
import com.faltenreich.skeletonlayout.Skeleton
import com.faltenreich.skeletonlayout.SkeletonLayout
import com.faltenreich.skeletonlayout.applySkeleton
import com.google.gson.Gson
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class PostFavorites_Fragment : Fragment(), ItemListener {

    lateinit var favoritesPostViewModel: FavoritesPostViewModel

    val favoritesPostsRV: RecyclerView
        get() = activity!!.findViewById(R.id.favoritesPostsRV)

    val mSwipeRefreshLayoutFavoritePost: SwipeRefreshLayout
        get() = activity!!.findViewById(R.id.mSwipeRefreshLayoutFavoritePost)


    private lateinit var skeleton: Skeleton

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View?{
        // Inflate the layout for this fragment
        return inflater?.inflate(
            R.layout.tab_fragment_post_favorites,
            container, false)

    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        favoritesPostViewModel = ViewModelProvider(this).get(FavoritesPostViewModel::class.java)

        showSkelton()

        mSwipeRefreshLayoutFavoritePost.setOnRefreshListener(SwipeRefreshLayout.OnRefreshListener {

            showSkelton()

            favoritesPostViewModel.getFavoritePostList().observe(activity!!, Observer {
                favoritesPostsRV.adapter = null
                favoritesPostsRV.layoutManager = LinearLayoutManager(activity!!)
                favoritesPostsRV.adapter = FavoritePostAdapter(it, activity!!, this!!)
            })

            mSwipeRefreshLayoutFavoritePost.setRefreshing(false)
        })

        favoritesPostViewModel.favoritesPost.observe(activity!!, Observer {
            favoritesPostsRV.layoutManager = LinearLayoutManager(activity!!)
            favoritesPostsRV.adapter = FavoritePostAdapter(it, activity!!, this!!)
        })


    }

    override fun itemClicked(position: Int, context: Context) {


    }

    fun showSkelton(){
        skeleton = activity!!.findViewById<SkeletonLayout>(R.id.skeletonLayoutFavoritesPost)

        skeleton = favoritesPostsRV.applySkeleton(R.layout.row_post_gray)

        skeleton.maskCornerRadius = 8.0f

        skeleton.showSkeleton()
    }


}