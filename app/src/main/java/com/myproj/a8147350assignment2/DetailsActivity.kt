package com.myproj.a8147350assignment2

import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.google.gson.JsonParser

class DetailsActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_details)
        findViewById<TextView>(R.id.btnBack).setOnClickListener {
            finish()
        }


        val tvDetails =
            findViewById<TextView>(R.id.tvDetails)

        val entityJson = intent.getStringExtra("entity")

        if (entityJson.isNullOrEmpty()) {
            tvDetails.text = "No details available"
            return
        }

        try {
            val entity = JsonParser.parseString(entityJson)
                .asJsonObject

            val details = entity.entrySet()
                .joinToString("\n\n") {
                    "${it.key}:\n${it.value.asString}"
                }

            tvDetails.text = details

        } catch (e: Exception) {
            tvDetails.text = "Unable to display details"
        }
    }
}
