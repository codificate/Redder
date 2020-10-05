package com.deviget.redder.di

import com.deviget.redder.di.RetrofitApiCreator.createRedderService
import com.deviget.redder.domain.action.AfterPost
import com.deviget.redder.domain.action.FetchPosts
import com.deviget.redder.domain.action.NextPosts

fun createFetchPost(): FetchPosts {
    return FetchPosts(createRedderService())
}

fun createNextPost(): NextPosts {
    return NextPosts(createRedderService())
}

fun createAfterPost(): AfterPost {
    return AfterPost(createRedderService())
}