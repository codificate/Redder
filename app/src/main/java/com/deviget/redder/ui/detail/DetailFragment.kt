package com.deviget.redder.ui.detail

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.bumptech.glide.Glide
import com.deviget.redder.R
import com.deviget.redder.domain.model.Post
import kotlinx.android.synthetic.main.fragment_detail.*

class DetailFragment : Fragment() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_detail, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val post = arguments?.getParcelable<Post>(EXTRA_POST_KEY)

        post?.let {
            detailUserName.text = it.author
            detailTitle.text = it.title
            it.thumbnail?.let { thumbnail ->
                Glide.with(view.context)
                    .load(thumbnail)
                    .fitCenter()
                    .into(detailThumbnail)
                detailThumbnail.visibility = View.VISIBLE
            }
        }
    }

    companion object {
        const val EXTRA_POST_KEY = "extra_post_key"
    }
}