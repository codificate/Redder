package com.deviget.redder.domain.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Post(
    val author: String,
    val created: Long,
    val domain: String,
    val id: String,
    val num_comments: Int,
    val readed: Boolean,
    val subreddit_name_prefixed: String,
    val thumbnail: String,
    val title: String,
    val url: String
) : Parcelable