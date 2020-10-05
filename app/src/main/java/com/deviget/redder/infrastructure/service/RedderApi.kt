package com.deviget.redder.infrastructure.service

import com.deviget.redder.infrastructure.representation.RedderResponse
import io.reactivex.Maybe
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Query

interface RedderApi {
    @GET("top.json?limit=10")
    fun topList(): Single<RedderResponse>

    @GET("top.json?limit=10")
    fun nextPostList(@Query("after") after: String): Maybe<RedderResponse>
}