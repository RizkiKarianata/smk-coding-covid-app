package com.example.challenge2.activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.challenge2.R
import android.view.Window
import android.view.WindowManager
import android.widget.Toast
import com.example.challenge2.MainActivity
import kotlinx.android.synthetic.main.activity_login.*

class LoginActivity : AppCompatActivity() {

    private var usernameInput : String = ""
    private var passwordInput : String = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        requestWindowFeature(Window.FEATURE_NO_TITLE)
        super.onCreate(savedInstanceState)
        window.setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN)
        setContentView(R.layout.activity_login)
        btnLogin.setOnClickListener { validationInput() }
        btnRegister.setOnClickListener { navigationToRegisterActivity() }
    }
    private fun navigationToRegisterActivity() {
        val intent = Intent(this, RegisterActivity::class.java)
        startActivity(intent)
    }
    private fun dataRegister() {
        val intent = Intent(this, MainActivity::class.java)
        startActivity(intent)
    }
    private fun validationInput(){
        val bundle = intent.extras
        usernameInput = usernameLog.text.toString()
        passwordInput = passwordLog.text.toString()
        when {
            usernameInput.isEmpty() -> usernameLog.error = "Nama Pengguna harus diisi"
            passwordInput.isEmpty() -> passwordLog.error = "Kata Sandi harus diisi"
            else -> {
                val datausername = bundle?.getString("username")
                val datapassword = bundle?.getString("password")
                if(usernameInput == datausername && passwordInput == datapassword) {
                    tampilToast("Berhasil masuk")
                    dataRegister()
                }else {
                    tampilToast("Gagal masuk")
                }
            }
        }
    }
    private fun tampilToast(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
    }
}
