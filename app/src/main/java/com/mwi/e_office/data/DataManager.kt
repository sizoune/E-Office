package com.mwi.e_office.data

import android.content.Context
import com.mwi.e_office.data.model.NewsRequest
import com.mwi.e_office.data.model.Response
import com.mwi.e_office.data.remote.ApiServiceInterface
import io.reactivex.Observable

class DataManager(
    private val context: Context,
    private val apiService: ApiServiceInterface = ApiServiceInterface.create()
) {
    fun getNews(newsReq: NewsRequest): Observable<Response> {
        return apiService.getNewsEvent(newsReq)
    }
}