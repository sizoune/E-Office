package com.mwi.e_office.data.remote

import com.mwi.e_office.BuildConfig
import com.mwi.e_office.data.model.NewsRequest
import com.mwi.e_office.data.model.Response
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.Body
import retrofit2.http.POST
import io.reactivex.Observable

interface ApiServiceInterface {

    @POST("event")
    fun getNewsEvent(@Body news: NewsRequest): Observable<Response>

    companion object Factory {
        fun create(): ApiServiceInterface {
            val retrofit = retrofit2.Retrofit.Builder()
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .baseUrl(BuildConfig.BASE_URL)
                .build()

            return retrofit.create(ApiServiceInterface::class.java)
        }
    }
}