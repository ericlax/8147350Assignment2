package com.myproj.a8147350assignment2

import android.content.Intent
import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.myproj.a8147350assignment2.data.remote.ApiService
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch
import retrofit2.HttpException
import javax.inject.Inject

@AndroidEntryPoint
class DashboardActivity : AppCompatActivity() {

    @Inject lateinit var apiService: ApiService

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_dashboard)

        val recyclerView =
            findViewById<RecyclerView>(R.id.recyclerView)

        val tvStatus =
            findViewById<TextView>(R.id.tvStatus)

        recyclerView.layoutManager =
            LinearLayoutManager(this)

        val keypass = intent.getStringExtra("keypass")

        if (keypass.isNullOrEmpty()) {
            tvStatus.text = "Missing login key. Please log in again."
            return
        }

        tvStatus.text = "Loading..."

        lifecycleScope.launch {
            try {
                val response = apiService.getDashboard(keypass)

                tvStatus.text =
                    "Total entities: ${response.entityTotal}"

                recyclerView.adapter =
                    EntityAdapter(response.entities) { entity ->

                        val intent = Intent(
                            this@DashboardActivity,
                            DetailsActivity::class.java
                        )

                        intent.putExtra("entity", entity.toString())
                        startActivity(intent)
                    }

            } catch (e: HttpException) {
                tvStatus.text = "Server error: ${e.code()}"
            } catch (e: Exception) {
                tvStatus.text = "Unable to load dashboard"
            }
        }
    }
}
