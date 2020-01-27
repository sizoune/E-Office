package com.mwi.e_office.ui.home

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.mwi.e_office.R
import com.mwi.e_office.data.model.DataBerita
import com.squareup.picasso.Picasso
import kotlinx.android.extensions.LayoutContainer
import kotlinx.android.synthetic.main.news_item.*

class MainAdapter(
    private val context: Context,
    private val dataNews: MutableList<DataBerita>,
    private val clickListener: (DataBerita) -> Unit
) : RecyclerView.Adapter<MainAdapter.NewsHolder>() {

    class NewsHolder(override val containerView: View) : RecyclerView.ViewHolder(containerView),
        LayoutContainer {
        fun bindItemtoView(
            data: DataBerita,
            clickListener: (DataBerita) -> Unit
        ) {
            tanggal.text = data.tanggal
            title.text = data.judul
            Log.d("gambar", data.gambar)
            Picasso.get().load(data.gambar).into(image)
            containerView.setOnClickListener { clickListener(data) }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NewsHolder {
        return NewsHolder(
            LayoutInflater.from(context).inflate(
                R.layout.news_item,
                parent,
                false
            )
        )
    }

    override fun getItemCount(): Int {
        return dataNews.size
    }

    override fun onBindViewHolder(holder: NewsHolder, position: Int) {
        holder.bindItemtoView(dataNews[position], clickListener)
    }
}