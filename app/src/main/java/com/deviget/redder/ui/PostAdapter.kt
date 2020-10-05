package com.deviget.redder.ui

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.deviget.redder.R
import com.deviget.redder.domain.model.Post

class PostAdapter(private val postPublisher: PostBehaviorPublisher) : RecyclerView.Adapter<PostViewHolder>(){

    private val postList: MutableList<Post> = mutableListOf()

    init {
        setHasStableIds(true)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PostViewHolder {
        return PostViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.post_item_layout, parent, false))
    }

    override fun getItemCount(): Int = postList.size


    override fun onBindViewHolder(holder: PostViewHolder, position: Int) {
        holder.bind(postList[position])
    }

    fun setPosts(posts: List<Post>){
        postList.clear()
        postList.addAll(posts)
        notifyDataSetChanged()
    }
}