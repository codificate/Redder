package com.deviget.redder.ui

import io.reactivex.Observable
import io.reactivex.subjects.PublishSubject

interface PostBehaviorPublisher {
    fun removeAll()
    fun removeItem(position: Int)
    fun itemWasRead(position: Int)
    fun observe(): Observable<Pair<Int?, PostItemReaction>>
}

object DefaultPostBehaviorReaction: PostBehaviorPublisher {

    private val publisher = PublishSubject.create<Pair<Int?, PostItemReaction>>()

    override fun removeAll() = publisher.onNext(Pair(null, PostItemReaction.REMOVE_ALL))

    override fun removeItem(position: Int) = publisher.onNext(Pair(position, PostItemReaction.REMOVE))

    override fun itemWasRead(position: Int) = publisher.onNext(Pair(position, PostItemReaction.WAS_READ))

    override fun observe(): Observable<Pair<Int?, PostItemReaction>> = publisher
}

enum class PostItemReaction {
    REMOVE,
    WAS_READ,
    REMOVE_ALL
}