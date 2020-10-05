package com.deviget.redder.infrastructure.representation

import com.google.gson.annotations.SerializedName

data class Children(
    @SerializedName("kind") val kind: String?,
    @SerializedName("data") val data: PostDataResponse
)