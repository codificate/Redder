package com.deviget.redder.ui.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.deviget.redder.domain.action.AfterPost
import com.deviget.redder.domain.action.FetchPosts
import com.deviget.redder.domain.action.NextPosts
import com.deviget.redder.domain.model.Post
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.rxkotlin.addTo
import io.reactivex.rxkotlin.subscribeBy
import io.reactivex.schedulers.Schedulers

const val MAX_POST_SIZE = 50

class PostViewModel(
    private val fetchPosts: FetchPosts,
    private val nextPosts: NextPosts,
    private val afterPost: AfterPost
) : ViewModel() {

    private val mutableListPost = MutableLiveData<List<Post>>()
    val listPosts: LiveData<List<Post>> = mutableListPost
    private var posts = mutableListOf<Post>()

    private val subscriptions = CompositeDisposable()

    var postListWasRefreshed = false

    init {
        fetchPosts()
    }

    fun loadMorePosts() {
        nextPosts(afterPost())
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeBy(onSuccess = ::setTopPost)
            .addTo(subscriptions)
    }

    fun resetPosts() {
        fetchPosts()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeBy(onSuccess = ::setTopPost)
            .addTo(subscriptions)
    }

    fun isLastPage() = posts.size < MAX_POST_SIZE

    private fun setTopPost(postResults: List<Post>) {
        if (posts.removeAll { postListWasRefreshed }) {
            postListWasRefreshed = false
        }
        posts.addAll(postResults)
        posts.sortBy { post: Post -> post.created }
        mutableListPost.postValue(posts)
    }

}