package com.deviget.redder.domain.repository

import com.deviget.redder.domain.model.Post
import io.reactivex.Maybe
import io.reactivex.Single

interface RedderRepository {
    fun top(): Single<List<Post>>
    fun nextPage(after: String): Maybe<List<Post>>
}