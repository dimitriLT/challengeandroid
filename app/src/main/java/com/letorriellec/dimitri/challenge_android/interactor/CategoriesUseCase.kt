package com.letorriellec.dimitri.challenge_android.interactor

import com.letorriellec.dimitri.challenge_android.model.Category

interface CategoriesUseCase {
    suspend fun loadCategories(): List<Category>?
}