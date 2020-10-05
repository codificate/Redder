package com.deviget.redder.ui

import io.reactivex.Observable
import io.reactivex.subjects.PublishSubject

interface PostBehaviorPublisher {
    fun removeAll()
    fun removeItem(id: String)
    fun observe(): Observable<Pair<String?, PostItemReaction>>
}

object DefaultPostBehaviorReaction: PostBehaviorPublisher {

    private val publisher = PublishSubject.create<Pair<String?, PostItemReaction>>()

    override fun removeAll() = publisher.onNext(Pair(null, PostItemReaction.REMOVE_ALL))

    override fun removeItem(id: String) = publisher.onNext(Pair(id, PostItemReaction.REMOVE))

    override fun observe(): Observable<Pair<String?, PostItemReaction>> = publisher
}

enum class PostItemReaction {
    REMOVE,
    REMOVE_ALL
}