package com.example.challenge2.activity

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.example.challenge2.R
import android.view.Window
import android.view.WindowManager
import android.widget.Toast
import com.firebase.ui.auth.AuthUI
import com.google.firebase.auth.FirebaseAuth
import kotlinx.android.synthetic.main.activity_login.*

class LoginActivity : AppCompatActivity(), View.OnClickListener {

    private var auth: FirebaseAuth? = null
    private val RC_SIGN_IN = 1

    private var usernameInput: String = ""
    private var passwordInput: String = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        requestWindowFeature(Window.FEATURE_NO_TITLE)
        super.onCreate(savedInstanceState)
        window.setFlags(
            WindowManager.LayoutParams.FLAG_FULLSCREEN,
            WindowManager.LayoutParams.FLAG_FULLSCREEN
        )
        setContentView(R.layout.activity_login)
        btnLogin.setOnClickListener { validationInput() }
        btnRegister.setOnClickListener { navigationToRegisterActivity() }
        progress.visibility = View.GONE
        btnGoogleLogin.setOnClickListener(this)
        auth = FirebaseAuth.getInstance()
        if (auth!!.currentUser == null) {
        } else {
            intent = Intent(applicationContext, MainActivity::class.java)
            startActivity(intent)
            finish()
        }
    }

    private fun navigationToRegisterActivity() {
        val intent = Intent(this, RegisterActivity::class.java)
        startActivity(intent)
    }

    private fun dataRegister() {
        val intent = Intent(this, MainActivity::class.java)
        startActivity(intent)
    }

    private fun validationInput() {
        val bundle = intent.extras
        usernameInput = usernameLog.text.toString()
        passwordInput = passwordLog.text.toString()
        when {
            usernameInput.isEmpty() -> usernameLog.error = "Nama Pengguna harus diisi"
            passwordInput.isEmpty() -> passwordLog.error = "Kata Sandi harus diisi"
            else -> {
                val datausername = bundle?.getString("username")
                val datapassword = bundle?.getString("password")
                if (usernameInput == datausername && passwordInput == datapassword) {
                    tampilToast("Berhasil masuk")
                    dataRegister()
                } else {
                    tampilToast("Gagal masuk")
                }
            }
        }
    }

    private fun tampilToast(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == RC_SIGN_IN) {
            if (resultCode == Activity.RESULT_OK) {
                Toast.makeText(
                    this, "Berhasil Masuk",
                    Toast.LENGTH_SHORT
                ).show()
                intent = Intent(
                    applicationContext,
                    MainActivity::class.java
                )
                startActivity(intent)
                finish()
            } else {
                progress.visibility = View.GONE
                Toast.makeText(
                    this, "Gagal Masuk",
                    Toast.LENGTH_SHORT
                ).show()
            }
        }
    }
    override fun onClick(v: View?) {
        startActivityForResult(
            AuthUI.getInstance()
                .createSignInIntentBuilder()
                .setAvailableProviders(listOf(AuthUI.IdpConfig.GoogleBuilder().build()))
                .setIsSmartLockEnabled(false)
                .build(),
            RC_SIGN_IN
        )
        progress.visibility = View.VISIBLE
    }
}
