package com.letorriellec.dimitri.challenge_android.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity;
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.letorriellec.dimitri.challenge_android.R
import com.letorriellec.dimitri.challenge_android.interactor.CategoriesUseCaseImpl
import com.letorriellec.dimitri.challenge_android.model.CategoryViewModel
import com.letorriellec.dimitri.challenge_android.model.HttpCallFailureException
import com.letorriellec.dimitri.challenge_android.model.NoNetworkException
import com.letorriellec.dimitri.challenge_android.model.ServerUnreachableException
import com.letorriellec.dimitri.challenge_android.presentation.CategoriesPresenter
import com.letorriellec.dimitri.challenge_android.presentation.CategoriesPresenterImpl
import com.letorriellec.dimitri.challenge_android.repository.CategoriesRemoteRepository
import com.letorriellec.dimitri.challenge_android.repository.CategoriesRepositoryImpl

import kotlinx.android.synthetic.main.activity_main.*

class MainCategoryActivity : AppCompatActivity() {

    private lateinit var categoriesRecyclerViewAdapter: CategoriesRecyclerViewAdapter
    private lateinit var categoriesPresenter: CategoriesPresenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        categoriesRecyclerView.layoutManager = LinearLayoutManager(this)
        categoriesRecyclerView.setHasFixedSize(true)
        categoriesRecyclerViewAdapter =
            CategoriesRecyclerViewAdapter()
        categoriesRecyclerView.adapter = categoriesRecyclerViewAdapter


        val categoriesRepository = CategoriesRepositoryImpl(CategoriesRemoteRepository())
        val categoriesInteractor = CategoriesUseCaseImpl(categoriesRepository)
        categoriesPresenter = CategoriesPresenterImpl(categoriesInteractor)

        categoriesPresenter.loadCategories()
    }

    private fun setDataInRecyclerView(it: List<CategoryViewModel>) {
        categoriesRecyclerViewAdapter.setData(it)
    }

    override fun onStart() {
        super.onStart()
        categoriesPresenter.onViewAttached(CategoriesViewImpl())


    }
    override fun onStop() {
        super.onStop()
        categoriesPresenter.onViewDetach()
    }

    inner class CategoriesViewImpl : CategoryView {
        override fun displayCategories(categories: List<CategoryViewModel>) {
            setDataInRecyclerView(categories)
        }

        override fun displayError(error: Throwable) {
            when (error) {
                is NoNetworkException -> displayNoNetworkError()
                is ServerUnreachableException -> displayServerUnreachableError()
                is HttpCallFailureException -> displayCallFailedError()
                else -> displayGenericError(error)
            }
        }

        override fun displayNoNetworkError() {
            Toast.makeText(this@MainCategoryActivity, "No network!", Toast.LENGTH_SHORT).show()
        }

        override fun displayServerUnreachableError() {
            Toast.makeText(this@MainCategoryActivity, "Server is unreachable!", Toast.LENGTH_SHORT).show()
        }

        override fun displayCallFailedError() {
            Toast.makeText(this@MainCategoryActivity, "Call failed!", Toast.LENGTH_SHORT).show()
        }

        override fun displayGenericError(error: Throwable) {
            Toast.makeText(this@MainCategoryActivity, error.message, Toast.LENGTH_SHORT).show()
        }

    }
}
