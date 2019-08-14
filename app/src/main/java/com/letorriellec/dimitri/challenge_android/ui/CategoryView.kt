package com.letorriellec.dimitri.challenge_android.ui

import com.letorriellec.dimitri.challenge_android.model.CategoryViewModel

interface CategoryView {
    fun displayCategories(categories: List<CategoryViewModel>)
    fun displayError(error: Throwable)
    fun displayNoNetworkError()
    fun displayServerUnreachableError()
    fun displayCallFailedError()
    fun displayGenericError(error: Throwable)
}
