package com.example.httpmethodsretrofitexample

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_details.*

class DetailsActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_details)

        val json_code = intent.getIntExtra("json_code_textview",-1)

        json_code_textview.text = json_code.toString()

        val results = intent.getStringExtra("json_results")

        json_results_textview.text = results

    }
}
