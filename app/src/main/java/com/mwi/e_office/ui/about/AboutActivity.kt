package com.mwi.e_office.ui.about

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.mwi.e_office.R
import kotlinx.android.synthetic.main.toolbar.*

class AboutActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_about)

        setSupportActionBar(toolbar)
        supportActionBar!!.title = "About"
        supportActionBar!!.setDisplayHomeAsUpEnabled(true)
    }
}
