package com.deviget.redder.infrastructure.representation

import com.google.gson.annotations.SerializedName

data class RedderResponse(
    @SerializedName("data") val data: Data,
    @SerializedName("kind") val kind: String
)