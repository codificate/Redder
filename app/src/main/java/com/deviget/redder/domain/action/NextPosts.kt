package com.deviget.redder.domain.action

import com.deviget.redder.domain.model.Post
import com.deviget.redder.domain.repository.RedderRepository
import io.reactivex.Maybe

class NextPosts(private val apiRepository : RedderRepository) {
    operator fun invoke(after: String): Maybe<List<Post>> {
        return apiRepository.nextPage(after)
    }
}