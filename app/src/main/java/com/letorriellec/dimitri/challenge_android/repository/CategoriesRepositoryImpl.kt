package com.letorriellec.dimitri.challenge_android.repository

import com.letorriellec.dimitri.challenge_android.model.Category
import com.letorriellec.dimitri.challenge_android.model.NetworkException

class CategoriesRepositoryImpl(private val categoriesRemoteRepository: CategoriesRemoteRepository) :
    CategoriesRepository {
    override suspend fun executeLoadCategories(): List<Category>? {
        try {
            return categoriesRemoteRepository.getCategoriesAsync()?.body()?.resources

        } catch (e: Throwable) {
            throw NetworkException(e)
        }
    }
}