package com.letorriellec.dimitri.challenge_android.repository

import com.letorriellec.dimitri.challenge_android.model.Ressources
import com.letorriellec.dimitri.challenge_android.network.RetrofitFactory
import retrofit2.Response

class CategoriesRemoteRepository {

    suspend fun getCategoriesAsync(): Response<Ressources>? {
        return RetrofitFactory.getInstance()?.loadCategoriesAsync()?.await()
    }
}