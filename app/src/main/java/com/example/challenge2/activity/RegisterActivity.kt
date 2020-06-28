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

    private lateinit var auth : FirebaseAuth
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
        auth = FirebaseAuth.getInstance()

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
                    val ref = FirebaseDatabase.getInstance().getReference("user")
                    val userId = ref.push().key
                    val users = UsersModel(userId, name, username, email, telephone, password, address)
                    if (userId != null) {
                        ref.child(userId).setValue(users).addOnCompleteListener {
                            registerUser(email, password)
                        }
                    }
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
