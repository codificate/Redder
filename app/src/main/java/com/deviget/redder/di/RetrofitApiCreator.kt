package com.deviget.redder.di

import com.deviget.redder.domain.repository.RedderRepository
import com.deviget.redder.domain.service.RedderService
import com.deviget.redder.infrastructure.RestModule
import com.deviget.redder.infrastructure.service.RedderApi

object RetrofitApiCreator {

    fun createRedderService() : RedderRepository {
        return RedderService(createApiClient())
    }

    private fun createApiClient(): RedderApi {
        return RestModule.createApi(RedderApi::class.java, "https://www.reddit.com/")
    }

}