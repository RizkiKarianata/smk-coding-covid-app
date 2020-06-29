package com.example.challenge2.fragment

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.Nullable
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import com.bumptech.glide.Glide
import com.example.challenge2.R
import com.example.challenge2.activity.EditProfileActivity
import com.example.challenge2.activity.LoginActivity
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.storage.StorageReference
import com.example.challenge2.model.UsersModel
import com.example.challenge2.session.SessionData
import com.example.challenge2.viewmodel.UsersViewModel
import kotlinx.android.synthetic.main.fragment_profile.*

class ProfileFragment : Fragment() {

    private lateinit var auth : FirebaseAuth
    private lateinit var userReference : DatabaseReference
    private lateinit var storageRef : StorageReference

    private val mRef = FirebaseDatabase.getInstance()
    private val viewModel by viewModels<UsersViewModel>()
    lateinit var users: ArrayList<UsersModel>

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_profile, container, false)
    }
    override fun onViewCreated(
        view: View,
        @Nullable savedInstanceState: Bundle?
    ) {
        super.onViewCreated(view, savedInstanceState)
        btnChangeAccount.setOnClickListener { redirectChangeAccount()}
        auth = FirebaseAuth.getInstance()
        btnLogout.setOnClickListener {
            auth.signOut()
            Intent(context, LoginActivity::class.java).also {
                it.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
                startActivity(it)
            }
        }
        viewModel.init(requireContext())
        if (auth.currentUser!!.uid.isNotEmpty()) {
            val user = auth.currentUser
            val uid = user?.uid ?: SessionData["UserData"]

            getData(uid)
            viewModel.allUsers.observe(viewLifecycleOwner, Observer { usr ->
                usr.let {
                    for (h in it) {
                        if (h.uid == uid) {
                            profileNama.text = h.nama
                            tv_name.text = h.username
                            tv_address.text = h.alamat
                            profileTelepon.text = h.telepon
                            profileEmail.text = h.email
                        }
                    }
                }
            })
        }
    }
    private fun redirectChangeAccount() {
        val intent = Intent(context, EditProfileActivity::class.java)
        startActivity(intent)
    }
    private fun getData(uid: String?) {
        mRef.getReference("users").addValueEventListener(object : ValueEventListener {
            override fun onCancelled(p0: DatabaseError) {
                TODO("Not yet implemented")
            }

            override fun onDataChange(p0: DataSnapshot) {
                users = ArrayList()
                if (p0.exists()) {
                    for (h in p0.children) {
                        val user: UsersModel? = h.getValue(UsersModel::class.java)
                            users.add(user!!)
                    }
                    viewModel.insertAll(users)
                }
            }
        })
    }
}
