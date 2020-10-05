package com.deviget.redder.domain.action

import com.deviget.redder.domain.model.Post
import com.deviget.redder.domain.repository.RedderRepository
import io.reactivex.Single

class FetchPosts(private val apiRepository : RedderRepository) {
    operator fun invoke(): Single<List<Post>> {
        return apiRepository.top()
    }
}