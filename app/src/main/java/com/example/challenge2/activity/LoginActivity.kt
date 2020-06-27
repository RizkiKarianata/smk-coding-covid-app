package com.example.challenge2.activity

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.content.pm.PackageInfo
import android.content.pm.PackageManager
import android.os.Bundle
import android.util.Base64
import android.util.Patterns
import android.view.View
import android.view.Window
import android.view.WindowManager
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.FragmentActivity
import com.example.challenge2.R
import com.facebook.CallbackManager
import com.facebook.FacebookCallback
import com.facebook.FacebookException
import com.facebook.login.LoginManager
import com.facebook.login.LoginResult
import com.firebase.ui.auth.AuthUI
import com.google.firebase.auth.FacebookAuthProvider
import com.google.firebase.auth.FirebaseAuth
import kotlinx.android.synthetic.main.activity_login.*
import java.security.MessageDigest
import java.security.NoSuchAlgorithmException
import java.util.*


class LoginActivity : AppCompatActivity(), View.OnClickListener {

    private lateinit var auth: FirebaseAuth
    private val RC_SIGN_IN = 1
    var callbackManager = CallbackManager.Factory.create()

    override fun onCreate(savedInstanceState: Bundle?) {
        requestWindowFeature(Window.FEATURE_NO_TITLE)
        super.onCreate(savedInstanceState)
        window.setFlags(
            WindowManager.LayoutParams.FLAG_FULLSCREEN,
            WindowManager.LayoutParams.FLAG_FULLSCREEN
        )
        setContentView(R.layout.activity_login)
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
        btnLogin.setOnClickListener {
            val emails = emailLog.text.toString().trim()
            val passwords = passwordLog.text.toString().trim()
            when {
                emails.isEmpty() -> emailLog.error = "Alamat Email harus diisi"
                !Patterns.EMAIL_ADDRESS.matcher(emails).matches() -> emailLog.error = "Alamat Email tidak lengkap"
                passwords.isEmpty() -> passwordLog.error = "Kata Sandi harus diisi"
                passwords.length < 6 -> passwordLog.error = "Kata sandi harus lebih dari 6 karakter"
                else -> {
                    loginUser(emails, passwords)
                }
            }
        }
        printHashKey(this)
        facebook_login_button.setOnClickListener {
            facebookLogin()
        }
    }
    fun facebookLogin() {
        LoginManager.getInstance().logInWithReadPermissions(this, Arrays.asList("public_profile", "email"))
        LoginManager.getInstance().registerCallback(callbackManager,object :
            FacebookCallback<LoginResult> {
            override fun onSuccess(result: LoginResult?) {
                firebaseAuthWithFacebook(result)
            }

            override fun onCancel() {

            }

            override fun onError(error: FacebookException?) {

            }

        })
    }
    fun firebaseAuthWithFacebook(result: LoginResult?) {
        var credential = FacebookAuthProvider.getCredential(result?.accessToken?.token!!)
        FirebaseAuth.getInstance().signInWithCredential(credential).addOnCompleteListener { task ->
            if(task.isSuccessful) {
                println("Facebook  Login Success")
            }
        }
    }
    fun printHashKey(pContext: Context) {
        try {
            val info: PackageInfo = pContext.getPackageManager()
                .getPackageInfo(pContext.getPackageName(), PackageManager.GET_SIGNATURES)
            for (signature in info.signatures) {
                val md: MessageDigest = MessageDigest.getInstance("SHA")
                md.update(signature.toByteArray())
                val hashKey = String(Base64.encode(md.digest(), 0))
                println("printHashKey() Hash Key: $hashKey")
            }
        } catch (e: NoSuchAlgorithmException) {

        } catch (e: Exception) {

        }
    }

    private fun loginUser(emails: String, passwords: String) {
        auth.signInWithEmailAndPassword(emails, passwords)
            .addOnCompleteListener(this) {
                if(it.isSuccessful) {
                    Intent(this, MainActivity::class.java).also {
                        it.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
                        startActivity(it)
                    }
                }else {
                    tampilToast("Gagal Masuk")
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
    private fun navigationToRegisterActivity() {
        val intent = Intent(this, RegisterActivity::class.java)
        startActivity(intent)
    }

    private fun tampilToast(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        callbackManager.onActivityResult(requestCode, resultCode, data)
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
