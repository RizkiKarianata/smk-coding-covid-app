package com.example.challenge2.activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Patterns
import com.example.challenge2.R
import android.view.Window
import android.view.WindowManager
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import kotlinx.android.synthetic.main.activity_register.*

class RegisterActivity : AppCompatActivity() {

    private lateinit var auth : FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        requestWindowFeature(Window.FEATURE_NO_TITLE)
        super.onCreate(savedInstanceState)
        window.setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN)
        setContentView(R.layout.activity_register)
        btnLoginCovid.setOnClickListener { navigationToLoginActivity() }
        auth = FirebaseAuth.getInstance()

        btnRegisterCovid.setOnClickListener {
            val namec = nameReg.text.toString().trim()
            val usernamec = usernameReg.text.toString().trim()
            val emailc = emailReg.text.toString().trim()
            val telephonec = telephoneReg.text.toString().trim()
            val addressc = addressReg.text.toString().trim()
            val passwordc = passwordReg.text.toString().trim()
            when {
                namec.isEmpty() -> nameReg.error = "Nama Lengkap harus diisi"
                usernamec.isEmpty() -> usernameReg.error = "Nama Pengguna harus diisi"
                emailc.isEmpty() -> emailReg.error = "Alamat Email harus diisi"
                !Patterns.EMAIL_ADDRESS.matcher(emailc).matches() -> emailReg.error = "Alamat Email tidak lengkap"
                telephonec.isEmpty() -> telephoneReg.error = "Nomor Telepon harus diisi"
                addressc.isEmpty() -> addressReg.error = "Alamat harus diisi"
                passwordc.isEmpty() -> passwordReg.error = "Kata Sandi harus diisi"
                passwordc.length < 6 -> passwordReg.error = "Kata sandi harus lebih dari 6 karakter"
                else -> {
                    registerUser(emailc, passwordc)
                }
            }
        }
    }
    private fun navigationToLoginActivity() {
        val intent = Intent(this, LoginActivity::class.java)
        startActivity(intent)
    }
    private fun tampilToast(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
    }
    private fun registerUser(emailc: String, passwordc: String) {
        auth.createUserWithEmailAndPassword(emailc,passwordc)
            .addOnCompleteListener(this) {
                if(it.isSuccessful) {
                    Intent(this, MainActivity::class.java).also {
                        it.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
                        startActivity(it)
                    }
                }else {
                    tampilToast("Gagal Mendaftar")
                }
            }
    }
    override fun onStart() {
        super.onStart()
        if(auth.currentUser != null) {
            Intent(this, MainActivity::class.java).also {
                it.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
                startActivity(it)
            }
        }
    }
}
