package com.app.postdemo.adapter


import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.app.postdemo.Interface.ItemListener
import com.app.postdemo.models.Post
import com.app.postdemo.models.PostComment
import com.example.postdemo.R

/**
 * Created by Dheeraj Singh Bhadoria
 */

class PostCommentAdapter (var postCommentItemList: List<PostComment>,
                          var context: Context,
                          val itemClickListner: ItemListener
) : RecyclerView.Adapter<PostCommentAdapter.NameViewHolder>() {


  override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NameViewHolder
  {
    val view = LayoutInflater.from(parent.getContext())
      .inflate(R.layout.row_comment, parent, false)

    //return ViewHolder
    return NameViewHolder(view)
  }

  override fun onBindViewHolder(holder: NameViewHolder, position: Int) {
    holder.bindData(postCommentItemList,
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
    return postCommentItemList.size
  }

  class NameViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)
  {
    var emailId = itemView.findViewById<TextView>(R.id.emailId)
    var name = itemView.findViewById<TextView>(R.id.name)
    var body = itemView.findViewById<TextView>(R.id.body)

    fun bindData(postCommentItemList: List<PostComment>,
                 itemClickListner: ItemListener,
                 context: Context,
                 position: Int
    ) {

      emailId?.setText(postCommentItemList.get(position).email.capitalize())
      name?.setText(postCommentItemList.get(position).name.capitalize())
      body?.setText(postCommentItemList.get(position).body.capitalize())


      itemView.setOnClickListener(){
        itemClickListner.itemClicked(position, context)
      }

    }
  }
}