package com.example.challenge2

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Window
import android.view.WindowManager
import kotlinx.android.synthetic.main.activity_login.*

class LoginActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        requestWindowFeature(Window.FEATURE_NO_TITLE)
        super.onCreate(savedInstanceState)
        window.setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN)
        setContentView(R.layout.activity_login)
        btnSkip.setOnClickListener { navigationToMainActivity() }
        btnSignUp.setOnClickListener { navigationToRegisterActivity() }
    }
    private fun navigationToMainActivity() {
        val intent = Intent(this, MainActivity::class.java)
        startActivity(intent)
    }
    private fun navigationToRegisterActivity() {
        val intent = Intent(this, RegisterActivity::class.java)
        startActivity(intent)
    }
}
