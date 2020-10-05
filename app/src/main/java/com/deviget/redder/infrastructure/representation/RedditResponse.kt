package com.deviget.redder.infrastructure.representation

import com.google.gson.annotations.SerializedName

data class RedditResponse(
    @SerializedName("data") val `data`: Data,
    @SerializedName("data") val kind: String
)