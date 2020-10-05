package com.deviget.redder.ui

import androidx.fragment.app.FragmentActivity
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import com.deviget.redder.di.createAfterPost
import com.deviget.redder.di.createFetchPost
import com.deviget.redder.di.createNextPost

object PostViewModelFactory {

    internal fun create(owner: FragmentActivity): PostViewModel {
        return ViewModelProviders.of(owner).get(PostViewModel::class.java)
    }

    @Suppress("UNCHECKED_CAST")
    private fun createFactory(): ViewModelProvider.Factory {
        return object : ViewModelProvider.Factory {
            override fun <T : ViewModel?> create(modelClass: Class<T>): T {
                return PostViewModel(
                    createFetchPost(),
                    createNextPost(),
                    createAfterPost()
                ) as T
            }
        }
    }
}