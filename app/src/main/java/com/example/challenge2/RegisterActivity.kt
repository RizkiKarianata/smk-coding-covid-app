package com.example.challenge2

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Window
import android.view.WindowManager
import kotlinx.android.synthetic.main.activity_register.*
import android.widget.Toast

class RegisterActivity : AppCompatActivity() {

    private var nameInput : String = ""
    private var usernameInput : String = ""
    private var emailInput : String = ""
    private var telephoneInput : String = ""
    private var addressInput : String = ""
    private var passwordInput : String = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        requestWindowFeature(Window.FEATURE_NO_TITLE)
        super.onCreate(savedInstanceState)
        window.setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN)
        setContentView(R.layout.activity_register)
        btnRegisterCovid.setOnClickListener { validationInput() }
        btnLoginCovid.setOnClickListener { navigationToLoginActivity() }
    }
    private fun navigationToLoginActivity() {
        val intent = Intent(this, LoginActivity::class.java)
        startActivity(intent)
    }
    private fun validationInput(){
        nameInput = nameReg.text.toString()
        usernameInput = usernameReg.text.toString()
        emailInput = emailReg.text.toString()
        telephoneInput = telephoneReg.text.toString()
        addressInput = addressReg.text.toString()
        passwordInput = passwordReg.text.toString()
        when {
            nameInput.isEmpty() -> nameReg.error = "Name cannot be empty"
            usernameInput.isEmpty() -> usernameReg.error = "Username cannot be empty"
            emailInput.isEmpty() -> emailReg.error = "Email cannot be empty"
            telephoneInput.isEmpty() -> telephoneReg.error = "Telephone cannot be empty"
            addressInput.isEmpty() -> addressReg.error = "Address cannot be empty"
            passwordInput.isEmpty() -> passwordReg.error = "Password cannot be empty"
            else -> {
                tampilToast("Navigation to Login Activity")
                goToLoginActivity()
            }
        }
    }
    private fun tampilToast(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
    }

    private fun goToLoginActivity() {
        val intent = Intent(this, LoginActivity::class.java)
        val bundle = Bundle()
        bundle.putString("name", nameInput)
        bundle.putString("username", usernameInput)
        bundle.putString("email", emailInput)
        bundle.putString("telephone", telephoneInput)
        bundle.putString("address", addressInput)
        bundle.putString("password", passwordInput)
        intent.putExtras(bundle)
        startActivity(intent)
    }
}