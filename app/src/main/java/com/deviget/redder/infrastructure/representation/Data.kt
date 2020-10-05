package com.deviget.redder.infrastructure.representation

import com.google.gson.annotations.SerializedName

data class Data(
    @SerializedName("after") val after: String?,
    @SerializedName("children") val children: List<Children>?,
    @SerializedName("dist") val dist: Int?,
    @SerializedName("modhash") val modhash: String?
)