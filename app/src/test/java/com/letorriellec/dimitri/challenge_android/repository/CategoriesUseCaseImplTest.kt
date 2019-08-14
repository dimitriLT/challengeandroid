package com.letorriellec.dimitri.challenge_android.repository

import com.letorriellec.dimitri.challenge_android.interactor.CategoriesUseCase
import com.letorriellec.dimitri.challenge_android.interactor.CategoriesUseCaseImpl
import com.letorriellec.dimitri.challenge_android.model.Category
import io.mockk.MockKAnnotations
import io.mockk.coEvery
import io.mockk.impl.annotations.MockK
import kotlinx.coroutines.runBlocking
import okhttp3.MediaType
import okhttp3.ResponseBody
import org.junit.Assert.assertEquals
import org.junit.Assert.assertNotNull
import org.junit.Before
import org.junit.Test
import retrofit2.HttpException
import retrofit2.Response

class CategoriesUseCaseImplTest {

    @MockK lateinit var repository: CategoriesRepository

    private lateinit var useCase: CategoriesUseCase

    @Before fun setUp() {
        MockKAnnotations.init(this)
        useCase = CategoriesUseCaseImpl(
            repository
        )
    }

    @Test fun `when load should get categories`() = runBlocking {
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
        coEvery { repository.executeLoadCategories() } returns categories


        val result = useCase.loadCategories()
        assertNotNull(result)
        assert(result is List<Category>)
        assertEquals(result, categories)
    }

    @Test fun `when load should get empty list`() = runBlocking {
        val categories = emptyList<Category>()

        coEvery { repository.executeLoadCategories() } returns categories

        val result = useCase.loadCategories()
        assertNotNull(result)
        assert(result is List<Category>)
        assertEquals(result, categories)
    }

    @Test fun `when load throws an exception`() = runBlocking {

        coEvery { repository.executeLoadCategories() } throws HttpException(
            Response.error<Any>(
                500, ResponseBody.create(MediaType.parse("text/plain"), "Mock Server Error")
            )
        )

        try {
            val result = useCase.loadCategories()
        } catch (e: Exception) {
            assert(e is HttpException)
        }

    }

}