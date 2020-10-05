package com.deviget.redder.ui

import android.view.View
import android.view.View.GONE
import android.view.View.VISIBLE
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.deviget.redder.R
import com.deviget.redder.domain.model.Post
import com.deviget.redder.utils.TimeHelper

class PostViewHolder(
    private val view: View,
    private val dismiss: (Int) -> Unit,
    private val wasRead: (Int) -> Unit
) : RecyclerView.ViewHolder(view) {

    private val prefix = view.findViewById<TextView>(R.id.redder_prefix)
    private val userName = view.findViewById<TextView>(R.id.redder_username)
    private val createdAt = view.findViewById<TextView>(R.id.redder_created_at)
    private val redderTitle = view.findViewById<TextView>(R.id.redder_title)
    private val commentsNumber = view.findViewById<TextView>(R.id.redder_comments_number)
    private val imageThumbnail = view.findViewById<ImageView>(R.id.redder_image)
    private val noWasRead = view.findViewById<ImageView>(R.id.redder_no_read)
    private val dismissLabel = view.findViewById<TextView>(R.id.redder_dismiss_label)

    fun bind(post: Post, position: Int) {
        prefix.text = post.subreddit_name_prefixed
        userName.text = "u/" + post.author
        createdAt.text = TimeHelper.timeAgo(post.created)
        redderTitle.text = post.title
        commentsNumber.text = calculateCommentsK(post.num_comments)
        post.thumbnail?.let { thumbnail ->
            Glide.with(view.context)
                .load(thumbnail)
                .fitCenter()
                .into(imageThumbnail)
            imageThumbnail.visibility = VISIBLE
        }
        view.setOnClickListener {
            noWasRead.visibility = GONE
            wasRead(position)
        }

        dismissLabel.setOnClickListener {
            dismiss(position)
        }
    }

    private fun calculateCommentsK(comments: Int): String {
        return if (comments > 1000) {
            (comments / 1000).toString() + "k"
        } else {
            comments.toString()
        }
    }

}
