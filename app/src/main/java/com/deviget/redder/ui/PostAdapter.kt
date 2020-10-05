package com.deviget.redder.ui

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.deviget.redder.R
import com.deviget.redder.domain.model.Post

class PostAdapter(private val postPublisher: PostBehaviorPublisher) :
    RecyclerView.Adapter<PostViewHolder>() {

    private val postList: MutableList<Post> = mutableListOf()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PostViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.post_item_layout, parent, false)

        return PostViewHolder(view,
            { position: Int -> removePost(position) },
            { position: Int -> postWasRead(position) })
    }

    override fun getItemCount(): Int = postList.size

    override fun onBindViewHolder(holder: PostViewHolder, position: Int) {
        holder.bind(postList[position], position)
    }

    fun setPosts(posts: List<Post>, wasRefreshed: Boolean) {
        if (wasRefreshed) { postList.clear() }
        postList.addAll(posts)
        notifyDataSetChanged()
    }

    private fun postWasRead(position: Int) {
        val post = postList[position]
    }

    private fun removePost(position: Int) {
        postList.removeAt(position)
        notifyItemRemoved(position)
        notifyItemRangeChanged(position, postList.size)
    }
}
