package com.letorriellec.dimitri.challenge_android.repository

import com.letorriellec.dimitri.challenge_android.model.Category

interface CategoriesRepository {
    suspend fun executeLoadCategories(): List<Category>?
}