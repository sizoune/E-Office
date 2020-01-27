package com.mwi.e_office.ui.home

import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.mwi.e_office.R
import com.mwi.e_office.data.model.DataBerita
import com.mwi.e_office.data.model.NewsRequest
import com.mwi.e_office.ui.MainView
import com.mwi.e_office.ui.about.AboutActivity
import com.mwi.e_office.util.PaginationScrollListener
import io.reactivex.disposables.Disposable
import kotlinx.android.synthetic.main.activity_main.*
import org.jetbrains.anko.startActivity
import org.jetbrains.anko.toast

class MainActivity : AppCompatActivity(), MainView {
    private lateinit var presenter: MainPresenter
    private var disposable: Disposable? = null
    private val act = "list_berita"
    private var page = 1
    private var isLastPage: Boolean = false
    private lateinit var dataTambahan: MutableList<DataBerita>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        setSupportActionBar(toolbar)
        supportActionBar?.title = "Berita"
        init()
    }

    fun init() {
//        Picasso.get().isLoggingEnabled = true
        var newsReq = NewsRequest(act, page.toString())
        var isLoading: Boolean = false
        val layoutManager = LinearLayoutManager(this)

        presenter = MainPresenter(this, applicationContext)
        presenter.getNews(newsReq)
        disposable = presenter.disposable
        recyclerView.layoutManager = layoutManager
        recyclerView?.addOnScrollListener(object : PaginationScrollListener(layoutManager) {
            override fun isLastPage(): Boolean {
                return isLastPage
            }

            override fun isLoading(): Boolean {
                return isLoading
            }

            override fun loadMoreItems() {
                isLoading = true
                page++
                //you have to call loadmore items to get more data
                newsReq = NewsRequest(act, page.toString())
                presenter.getNews(newsReq)
                disposable = presenter.disposable
                isLoading = false
            }
        })

    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_about, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == R.id.action_about) {
            startActivity<AboutActivity>()
        }
        return super.onOptionsItemSelected(item)
    }

    override fun onError(msg: String) {
        toast(msg)
    }

    override fun showLoading() {
//        toast("loading")
    }

    override fun hideLoading() {
//        toast("selesai loading")
    }

    override fun showNews(data: List<DataBerita>) {
        if (data.size > 1) {
            Log.d("hal", page.toString())
            if (page == 1) {
                dataTambahan = data as MutableList<DataBerita>
                recyclerView.adapter = MainAdapter(this, dataTambahan) {
                    toast(it.isi.toString())
                }
            } else {
                dataTambahan.addAll(data)
                recyclerView.adapter?.notifyDataSetChanged()
            }

        } else {
            isLastPage = true
        }
    }

    override fun onDestroy() {
        disposable?.dispose()
        super.onDestroy()
    }
}
