package com.mwi.e_office.data.model

data class Response(
	val data_berita: List<DataBerita?>? = null,
	val text: String? = null,
	val status: Boolean? = null
)
