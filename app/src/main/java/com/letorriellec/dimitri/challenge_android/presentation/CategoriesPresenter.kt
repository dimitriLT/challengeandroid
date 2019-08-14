package com.letorriellec.dimitri.challenge_android.presentation

import com.letorriellec.dimitri.challenge_android.model.Category
import com.letorriellec.dimitri.challenge_android.ui.CategoryView

interface CategoriesPresenter : BasePresenter<CategoryView> {
    fun presentCategories(categories: List<Category>)
    fun presentError(cause: Throwable)
    fun presentEmpty()
    fun loadCategories()
}