package com.mwi.e_office.data.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class DataBerita(
	val hit: Int? = null,
	val thumb: String? = null,
	val id: Int? = null,
	val tanggal: String? = null,
	val judul: String? = null,
	val gambar: String? = null,
	val isi: String? = null
) : Parcelable
