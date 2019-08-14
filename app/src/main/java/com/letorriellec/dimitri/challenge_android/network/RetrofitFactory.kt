package com.letorriellec.dimitri.challenge_android.network

import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import com.letorriellec.dimitri.challenge_android.BuildConfig
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class RetrofitFactory {

    companion object {
        fun getInstance(): RetrofitService? {

            val okHttpClient = OkHttpClient.Builder().addInterceptor { chain ->
                val request = chain.request().newBuilder().header(
                    "Accept", "Application/JSON"
                ).build()

                chain.proceed(request)
            }.build()

            val retrofit = Retrofit.Builder().baseUrl(BuildConfig.API_ADDRESS)
                .addCallAdapterFactory(CoroutineCallAdapterFactory())
                .addConverterFactory(GsonConverterFactory.create()).client(okHttpClient).build()

            return retrofit.create(RetrofitService::class.java)
        }
    }
}