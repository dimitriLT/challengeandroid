package com.letorriellec.dimitri.challenge_android.interactor

import com.letorriellec.dimitri.challenge_android.model.Category
import com.letorriellec.dimitri.challenge_android.repository.CategoriesRepository

class CategoriesUseCaseImpl(private val repository: CategoriesRepository) : CategoriesUseCase {
    override suspend fun loadCategories(): List<Category>? {

        return repository.executeLoadCategories()
    }
}