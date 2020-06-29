package com.example.challenge2.activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Patterns
import com.example.challenge2.R
import android.view.Window
import android.view.WindowManager
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.example.challenge2.model.UsersModel
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.FirebaseDatabase
import kotlinx.android.synthetic.main.activity_register.*

class RegisterActivity : AppCompatActivity() {

    private val mRef = FirebaseDatabase.getInstance()
    private val mAuth = FirebaseAuth.getInstance()
    private lateinit var namec : EditText
    private lateinit var usernamec : EditText
    private lateinit var emailc : EditText
    private lateinit var telephonec : EditText
    private lateinit var addressc : EditText
    private lateinit var passwordc : EditText
    private lateinit var btnRegisterc : Button

    override fun onCreate(savedInstanceState: Bundle?) {
        requestWindowFeature(Window.FEATURE_NO_TITLE)
        super.onCreate(savedInstanceState)
        window.setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN)
        setContentView(R.layout.activity_register)
        namec = findViewById(R.id.nameReg)
        usernamec = findViewById(R.id.usernameReg)
        emailc = findViewById(R.id.emailReg)
        telephonec = findViewById(R.id.telephoneReg)
        addressc = findViewById(R.id.addressReg)
        passwordc = findViewById(R.id.passwordReg)
        btnRegisterc = findViewById(R.id.btnRegisterCovid)


        btnLoginCovid.setOnClickListener { navigationToLoginActivity() }

        btnRegisterc.setOnClickListener {
            val name = namec.text.toString().trim()
            val username = usernamec.text.toString().trim()
            val email = emailc.text.toString().trim()
            val telephone = telephonec.text.toString().trim()
            val address = addressc.text.toString().trim()
            val password = passwordc.text.toString().trim()
            when {
                name.isEmpty() -> namec.error = "Nama Lengkap harus diisi"
                username.isEmpty() -> usernamec.error = "Nama Pengguna harus diisi"
                email.isEmpty() -> emailc.error = "Alamat Email harus diisi"
                !Patterns.EMAIL_ADDRESS.matcher(email).matches() -> emailc.error = "Alamat Email tidak lengkap"
                telephone.isEmpty() -> telephonec.error = "Nomor Telepon harus diisi"
                address.isEmpty() -> addressc.error = "Alamat harus diisi"
                password.isEmpty() -> passwordc.error = "Kata Sandi harus diisi"
                password.length < 6 -> passwordc.error = "Kata sandi harus lebih dari 6 karakter"
                else -> {
                    mAuth.createUserWithEmailAndPassword(email,password)
                        .addOnCompleteListener(this) {
                            if(it.isSuccessful) {
                                val uid = mAuth.currentUser!!.uid
                                tampilToast("Berhasil Mendaftar")
                                simpanData(uid)
                            }else {
                                tampilToast("Gagal Mendaftar")
                            }
                        }
                }
            }
        }
    }
    private fun simpanData(uid: String) {
        val db = mRef.getReference("users/$uid")
        val name = namec.text.toString()
        val username = usernamec.text.toString()
        val email = emailc.text.toString()
        val telephone = telephonec.text.toString()
        val address = addressc.text.toString()
        val password = passwordc.text.toString()
        val user = UsersModel(uid, name, username, email, telephone, password, address)
        db.setValue(user).addOnCompleteListener {
            if (it.isSuccessful) {
                Intent(this, LoginActivity::class.java).also {
                    it.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
                    startActivity(it)
                }
            } else {
                tampilToast("Gagal Mendaftar")
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
    override fun onStart() {
        super.onStart()
        if(mAuth.currentUser != null) {
            Intent(this, MainActivity::class.java).also {
                it.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
                startActivity(it)
            }
        }
    }
}
