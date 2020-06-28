package com.example.challenge2.fragment

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.Nullable
import com.bumptech.glide.Glide
import com.example.challenge2.R
import com.example.challenge2.activity.EditProfileActivity
import com.example.challenge2.activity.LoginActivity
import com.google.firebase.auth.FirebaseAuth
import kotlinx.android.synthetic.main.fragment_profile.*

class ProfileFragment : Fragment() {

    private lateinit var auth : FirebaseAuth

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
    }
    private fun redirectChangeAccount() {
        val intent = Intent(context, EditProfileActivity::class.java)
        startActivity(intent)
    }
}
