package com.deviget.redder.domain.service

import com.deviget.redder.domain.model.Post
import com.deviget.redder.domain.repository.RedderRepository
import com.deviget.redder.infrastructure.representation.PostDataResponse
import com.deviget.redder.infrastructure.representation.RedderResponse
import com.deviget.redder.infrastructure.service.RedderApi
import io.reactivex.Maybe
import io.reactivex.Single

class RedderService(private val client: RedderApi) :
    RedderRepository {
    override fun top(): Single<List<Post>> {
        return client.topList().map { response: RedderResponse ->
            response.data.children.map { postData ->
                postData.data.toPost()
            }
        }
    }

    override fun nextPage(after: String): Maybe<List<Post>> {
        return client.nextPostList(after)
            .map { response: RedderResponse ->
            response.data.children.map { postData ->
                postData.data.toPost()
            }
        }
    }

    private fun PostDataResponse.toPost(): Post {
        return Post(
            this.author,
            this.created,
            this.domain,
            this.id,
            this.num_comments,
            this.visited,
            this.subreddit_name_prefixed,
            this.thumbnail,
            this.title,
            this.url
        )
    }
}