package com.mwi.e_office.ui

import com.mwi.e_office.data.model.DataBerita

interface MainView {
    fun onError(msg: String)
    fun showLoading()
    fun hideLoading()
    fun showNews(data: List<DataBerita>)
}