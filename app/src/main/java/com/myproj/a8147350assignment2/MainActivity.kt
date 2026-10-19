package com.myproj.a8147350assignment2

import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.TextView

import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle

import com.myproj.a8147350assignment2.ui.login.LoginState
import com.myproj.a8147350assignment2.ui.login.LoginViewModel

import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private val viewModel: LoginViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_main)

        val etUsername =
            findViewById<EditText>(R.id.etUsername)

        val etPassword =
            findViewById<EditText>(R.id.etPassword)

        val btnLogin =
            findViewById<Button>(R.id.btnLogin)

        val tvError =
            findViewById<TextView>(R.id.tvError)

        // Handle login button
        btnLogin.setOnClickListener {

            val username =
                etUsername.text.toString().trim()

            val password =
                etPassword.text.toString()

            tvError.visibility = View.GONE

            when {

                username.isEmpty() -> {
                    tvError.text = "Please enter your Student ID"
                    tvError.visibility = View.VISIBLE
                }

                password.isEmpty() -> {
                    tvError.text = "Please enter your first name"
                    tvError.visibility = View.VISIBLE
                }

                else -> {
                    viewModel.login(username, password)
                }
            }
        }

        // Observe login status
        lifecycleScope.launch {

            repeatOnLifecycle(Lifecycle.State.STARTED) {

                viewModel.loginState.collect { state ->

                    when (state) {

                        is LoginState.Idle -> {
                            // Waiting for user input
                        }

                        is LoginState.Loading -> {

                            btnLogin.isEnabled = false
                            btnLogin.text = "Logging in..."

                            tvError.visibility = View.GONE
                        }

                        is LoginState.Success -> {

                            btnLogin.isEnabled = true
                            btnLogin.text = "LOGIN"

                            tvError.text = "Login successful!"
                            val intent = android.content.Intent(
                                this@MainActivity,
                                DashboardActivity::class.java
                            )

                            intent.putExtra("keypass", state.keypass)
                            startActivity(intent)
                            finish()

                            tvError.setTextColor(
                                android.graphics.Color.GREEN
                            )
                            tvError.visibility = View.VISIBLE

                            // Step 4:
                            // Navigate to Dashboard
                            // using state.keypass
                        }

                        is LoginState.Error -> {

                            btnLogin.isEnabled = true
                            btnLogin.text = "LOGIN"

                            tvError.text = state.message
                            tvError.setTextColor(
                                android.graphics.Color.RED
                            )
                            tvError.visibility = View.VISIBLE
                        }
                    }
                }
            }
        }
    }
}
