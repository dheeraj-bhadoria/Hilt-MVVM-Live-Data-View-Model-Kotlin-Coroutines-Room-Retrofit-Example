package com.app.postdemo.fragment

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.os.Parcelable
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.app.postdemo.Interface.ItemListener
import com.app.postdemo.R
import com.app.postdemo.activity.PostComents
import com.app.postdemo.adapter.PostAdapter
import com.app.postdemo.models.Post
import com.app.postdemo.utils.AppUtils
import com.app.postdemo.viewmodels.PostViewModel
import com.faltenreich.skeletonlayout.Skeleton
import com.faltenreich.skeletonlayout.SkeletonLayout
import com.faltenreich.skeletonlayout.applySkeleton
import dagger.hilt.android.AndroidEntryPoint
import com.google.gson.Gson
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

@AndroidEntryPoint
class Post_Fragment  : Fragment() , ItemListener{

    lateinit var mainViewModel: PostViewModel
    var postList: List<Post>? = null

    val postsRV: RecyclerView
        get() = activity!!.findViewById(R.id.postsRV)

    val mSwipeRefreshLayoutPost: SwipeRefreshLayout
        get() = activity!!.findViewById(R.id.mSwipeRefreshLayoutPost)

    private lateinit var skeleton: Skeleton

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View?{
        // Inflate the layout for this fragment
        return inflater?.inflate(
            R.layout.tab_fragment_post,
            container, false)

    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        showSkelton()

        mainViewModel = ViewModelProvider(this).get(PostViewModel::class.java)

        mSwipeRefreshLayoutPost.setOnRefreshListener(SwipeRefreshLayout.OnRefreshListener {

            if(AppUtils.isInterConnectionIsAvailable(activity!!)){

            }else{
                AppUtils.showErrorDialog(activity!!, resources.getString(R.string.internetNotAvailableStr))
            }

            showSkelton()

            mainViewModel.postListLiveData.observe(activity!!, Observer {
                if(it.size==0){
                    Toast.makeText(activity!!, "No post found", Toast.LENGTH_LONG).show()
                }
                postsRV.adapter = null
                postList = it
                postsRV.layoutManager = LinearLayoutManager(activity!!)
                postsRV.adapter = PostAdapter(it, activity!!, this!!)

            })

            mSwipeRefreshLayoutPost.setRefreshing(false)
        })


        mainViewModel.postListLiveData.observe(activity!!, Observer {

            if(it.size==0){
                Toast.makeText(activity!!, "No post found", Toast.LENGTH_LONG).show()
            }

            postList = it
            postsRV.layoutManager = LinearLayoutManager(activity!!)

            postsRV.adapter = PostAdapter(it, activity!!, this!!)
        })

    }

    override fun itemClicked(position: Int, context: Context) {
        val intent = Intent(context, PostComents::class.java)
        val gson = Gson()
        val postJSON = gson.toJson(postList?.get(position))
        intent.putExtra("Post", postJSON)
        startActivity(intent)
        activity!!.overridePendingTransition(R.anim.enter, R.anim.exit)

    }

    // Example callback that hides skeleton
    private fun onDataLoaded() {
        skeleton.showOriginal()
    }

    fun showSkelton(){
        skeleton = activity!!.findViewById<SkeletonLayout>(R.id.skeletonLayoutPost)

        skeleton = postsRV.applySkeleton(R.layout.row_post_gray)

        skeleton.maskCornerRadius = 8.0f

        skeleton.showSkeleton()
    }

}