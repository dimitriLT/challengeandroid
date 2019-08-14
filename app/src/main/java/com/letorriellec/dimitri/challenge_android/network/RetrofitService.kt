package com.letorriellec.dimitri.challenge_android.network

import com.letorriellec.dimitri.challenge_android.model.Ressources
import kotlinx.coroutines.Deferred
import retrofit2.Response
import retrofit2.http.GET

interface RetrofitService {

    @GET("categories.json")
    fun loadCategoriesAsync(): Deferred<Response<Ressources>>

}