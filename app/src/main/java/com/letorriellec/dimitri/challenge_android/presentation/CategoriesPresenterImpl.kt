package com.letorriellec.dimitri.challenge_android.presentation

import com.letorriellec.dimitri.challenge_android.interactor.CategoriesUseCase
import com.letorriellec.dimitri.challenge_android.model.Category
import com.letorriellec.dimitri.challenge_android.model.CategoryViewModel
import com.letorriellec.dimitri.challenge_android.model.Parent
import com.letorriellec.dimitri.challenge_android.model.ParentViewModel
import com.letorriellec.dimitri.challenge_android.ui.CategoryView
import kotlinx.coroutines.*
import retrofit2.HttpException
import java.util.*

class CategoriesPresenterImpl(private val categoriesUseCase: CategoriesUseCase) :
    CategoriesPresenter {
    override fun onViewDetach() {
        mjob.cancel()
    }

    override fun onViewAttached(view: CategoryView) {
        this.view = view
    }

    private var view: CategoryView? = null
    private val mjob = Job()
    private val scope = CoroutineScope(Dispatchers.IO + mjob)

    private val handler = CoroutineExceptionHandler { _, throwable ->
        presentError(throwable)
    }

    override fun presentCategories(categories: List<Category>) {
        val viewModels = categories.map {
            it.toViewModel()
        }
        view?.displayCategories(ArrayList(viewModels))
    }

    override fun presentError(cause: Throwable) {
        view?.displayError(cause)
    }

    override fun presentEmpty() {
    }

    override fun loadCategories() {

        scope.launch(handler) {
            getFromCallback()

        }
    }

    private fun Category.toViewModel() = CategoryViewModel(
        id = id.toString(),
        resourceUri = resource_uri,
        resourceType = resource_type,
        name = name,
        parent = parent?.toViewModel() ,
        custom = custom,
        other = other,
        isDeleted = is_deleted
    )

    private fun Parent.toViewModel() = ParentViewModel(
        id = id.toString(),
        resourceUri = resource_uri,
        resourceType = resource_type
    )

    private suspend fun getFromCallback() {
        val response = categoriesUseCase.loadCategories()

        if (response != null) {
            withContext(Dispatchers.Main) {
                try {
                    presentCategories(response)

                } catch (e: HttpException) {
                    e.cause?.let { presentError(it) }
                } catch (e: Throwable) {
                    presentError(e)
                }
            }
        }
    }
}