package com.deviget.redder.infrastructure.representation

import com.google.gson.annotations.SerializedName

data class PostDataResponse(
    @SerializedName("author") val author: String?,
    @SerializedName("created") val created: Long,
    @SerializedName("domain") val domain: String?,
    @SerializedName("id") val id: String?,
    @SerializedName("num_comments") val num_comments: Int,
    @SerializedName("subreddit_name_prefixed") val subreddit_name_prefixed: String?,
    @SerializedName("thumbnail") val thumbnail: String?,
    @SerializedName("title") val title: String?,
    @SerializedName("url") val url: String?,
    @SerializedName("visited") val visited: Boolean
)