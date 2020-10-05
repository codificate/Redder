package com.deviget.redder.domain.action

import com.deviget.redder.domain.repository.RedderRepository

class AfterPost(private val apiRepository : RedderRepository) {
    operator fun invoke(): String {
        return apiRepository.after()
    }
}