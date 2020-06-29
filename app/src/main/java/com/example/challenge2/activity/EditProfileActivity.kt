package com.example.challenge2.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.challenge2.R
import android.view.Window
import android.view.WindowManager
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.storage.FirebaseStorage
import com.google.firebase.storage.StorageReference
import com.example.challenge2.model.UsersModel
import com.example.challenge2.viewmodel.UsersViewModel
import com.bumptech.glide.Glide
import android.widget.ArrayAdapter
import androidx.activity.viewModels
import android.provider.MediaStore
import android.net.Uri
import android.util.Patterns
import com.example.challenge2.util.tampilToast
import kotlinx.android.synthetic.main.activity_edit_profile.*

class EditProfileActivity : AppCompatActivity() {

    val viewModel by viewModels<UsersViewModel>()
    lateinit var mref: DatabaseReference
    lateinit var storageReference: StorageReference
    lateinit var uid: String
    lateinit var pass: String

    override fun onCreate(savedInstanceState: Bundle?) {
        requestWindowFeature(Window.FEATURE_NO_TITLE)
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_edit_profile)
        window.setFlags(
            WindowManager.LayoutParams.FLAG_FULLSCREEN,
            WindowManager.LayoutParams.FLAG_FULLSCREEN
        )
        viewModel.init(baseContext)
        mref = FirebaseDatabase.getInstance().reference
        storageReference = FirebaseStorage.getInstance().reference
        uid = FirebaseAuth.getInstance().currentUser!!.uid
        viewModel.allUsers.observeForever { list ->
            list.let { data ->
                for (dt in data) {
                    if (dt.uid == uid) {
                        nameEdit.setText(dt.nama)
                        usernameEdit.setText(dt.username)
                        telephoneEdit.setText(dt.telepon)
                        addressEdit.setText(dt.alamat)
                        emailEdit.setText(dt.email)
                        pass = dt.password
                    }
                }
            }
        }
        btnUpdateCovid.setOnClickListener { updateData() }
    }
    private fun updateData() {
        val name = nameEdit.text.toString().trim()
        val username = usernameEdit.text.toString().trim()
        val telephone = telephoneEdit.text.toString().trim()
        val address = addressEdit.text.toString().trim()
        when {
            name.isEmpty() -> nameEdit.error = "Nama Lengkap harus diisi"
            username.isEmpty() -> usernameEdit.error = "Nama Pengguna harus diisi"
            telephone.isEmpty() -> telephoneEdit.error = "Nomor Telepon harus diisi"
            address.isEmpty() -> addressEdit.error = "Alamat harus diisi"
            else -> {
                pushData()
            }
        }
    }
    private fun pushData() {
        val namePD = nameEdit.text.toString()
        val usernamePD = usernameEdit.text.toString()
        val telephonePD = telephoneEdit.text.toString()
        val addressPD = addressEdit.text.toString()
        val emailPD = emailEdit.text.toString()
        val user = UsersModel(uid, namePD, usernamePD, emailPD, telephonePD, pass, addressPD)
        mref.child("users").child(uid).setValue(user).addOnSuccessListener {
            viewModel.updateData(user)
            finish()
        }.addOnFailureListener {
            tampilToast(baseContext, it.message.toString())
        }
    }
}
