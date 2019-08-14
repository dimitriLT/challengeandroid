package com.letorriellec.dimitri.challenge_android.presentation

import com.letorriellec.dimitri.challenge_android.interactor.CategoriesUseCase
import com.letorriellec.dimitri.challenge_android.model.Category
import com.letorriellec.dimitri.challenge_android.model.CategoryViewModel
import com.letorriellec.dimitri.challenge_android.ui.CategoryView
import org.junit.Before
import org.junit.Test
import org.mockito.Mockito

class CategoriesPresenterImplTest {
    private lateinit var presenter: CategoriesPresenter
    private val view: CategoryView = Mockito.mock(CategoryView::class.java)
    private val useCase: CategoriesUseCase = Mockito.mock(CategoriesUseCase::class.java)
    private val throwable = Mockito.mock(Throwable::class.java)

    @Before fun setUp() {

        presenter = CategoriesPresenterImpl(useCase)
        presenter.onViewAttached(view)

    }

    @Test fun givenPresentTrips_ShouldDisplaySpaceTravelViewModel() {
        val categories = listOf(
            Category(
                custom = false,
                id = 1,
                is_deleted = false,
                name = "category",
                other = false,
                parent = null,
                resource_type = "resourceType",
                resource_uri = "resourceUri"

            )
        )

        presenter.presentCategories(categories)

        Mockito.verify(view).displayCategories(
            listOf(
                CategoryViewModel(
                    id = "1",
                    resourceUri = "resourceUri",
                    resourceType = "resourceType",
                    name = "category",
                    parent = null,
                    custom = false,
                    other = false,
                    isDeleted = false
                )
            )
        )
    }

    @Test
    fun givenPresentError_ShouldDoDisplayErrorMessage() {

        presenter.presentError(throwable)

        Mockito.verify(view).displayError(throwable)

    }
}