package com.app.postdemo.adapter


import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.app.postdemo.Interface.ItemListener
import com.app.postdemo.R
import com.app.postdemo.models.Post
import com.app.postdemo.utils.Constants
import com.bumptech.glide.Glide

import com.bumptech.glide.request.RequestOptions




/**
 * Created by Dheeraj Singh Bhadoria
 */

class PostAdapter (var postItemList: List<Post>,
                   var context: Context,
                   val itemClickListner: ItemListener
) : RecyclerView.Adapter<PostAdapter.NameViewHolder>() {


  override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NameViewHolder
  {
    val view = LayoutInflater.from(parent.getContext())
      .inflate(R.layout.row_post, parent, false)

    //return ViewHolder
    return NameViewHolder(view)
  }

  override fun onBindViewHolder(holder: NameViewHolder, position: Int) {
    holder.bindData(postItemList,
      itemClickListner,
      context,
      position)
  }

  /**
   * Returns item counts
   * or list size
   */

  override fun getItemCount(): Int
  {
    return postItemList.size
  }

  class NameViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)
  {
    var title = itemView.findViewById<TextView>(R.id.title)
    var body = itemView.findViewById<TextView>(R.id.body)
    var postImage = itemView.findViewById<ImageView>(R.id.postImage)

    fun bindData(alertItemList: List<Post>,
                 itemClickListner: ItemListener,
                 context: Context,
                 position: Int
    ) {

      title?.setText(alertItemList.get(position).title.capitalize())
      body?.setText(alertItemList.get(position).body.capitalize())

      itemView.setOnClickListener(){
        itemClickListner.itemClicked(position, context)
      }

      val requestOptions = RequestOptions()
      requestOptions.placeholder(R.drawable.placeholder)

      Glide.with(context)
        .setDefaultRequestOptions(requestOptions)
        .load(Constants.POSTER_IMAGE)
        .into(postImage);

    }
  }
}