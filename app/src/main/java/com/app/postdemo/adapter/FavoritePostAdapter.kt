package com.app.postdemo.adapter


import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.app.postdemo.Interface.ItemListener
import com.app.postdemo.models.FavoritesPost
import com.app.postdemo.models.Post
import com.example.postdemo.R

/**
 * Created by Dheeraj Singh Bhadoria
 */

class FavoritePostAdapter (var postItemList: List<FavoritesPost>,
                           var context: Context,
                           val itemClickListner: ItemListener
) : RecyclerView.Adapter<FavoritePostAdapter.NameViewHolder>() {


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

    fun bindData(alertItemList: List<FavoritesPost>,
                 itemClickListner: ItemListener,
                 context: Context,
                 position: Int
    ) {

      title?.setText(alertItemList.get(position).title.capitalize())
      body?.setText(alertItemList.get(position).body.capitalize())

      itemView.setOnClickListener(){
        itemClickListner.itemClicked(position, context)
      }

    }
  }
}