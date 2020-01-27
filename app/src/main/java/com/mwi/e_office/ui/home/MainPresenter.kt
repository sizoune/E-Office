package com.mwi.e_office.ui.home

import android.content.Context
import android.util.Log
import com.mwi.e_office.data.DataManager
import com.mwi.e_office.data.model.DataBerita
import com.mwi.e_office.data.model.NewsRequest
import com.mwi.e_office.data.model.Response
import com.mwi.e_office.ui.MainView
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers

class MainPresenter(
    private val view: MainView,
    context: Context
) {
    private val dataManager = DataManager(context)
    lateinit var disposable: Disposable

    fun getNews(newsReq: NewsRequest) {
        disposable = dataManager.getNews(newsReq)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .doOnSubscribe { view.showLoading() }
            .doOnComplete { view.hideLoading() }
            .doOnError { view.hideLoading() }
            .subscribe(
                { data ->
                    data.data_berita?.let { view.showNews(it as List<DataBerita>) }
                },
                { error ->
                    error.message?.let { view.onError(it) }
                }
            )
    }
}