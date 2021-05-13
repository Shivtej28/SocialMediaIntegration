package com.shivtej.socialmediaintegration

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.util.Log.WARN
import com.bumptech.glide.Glide
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.shivtej.socialmediaintegration.databinding.ActivityProfileBinding

class ProfileActivity : AppCompatActivity() {

    lateinit var binding: ActivityProfileBinding
    lateinit var mAuth: FirebaseAuth


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityProfileBinding.inflate(layoutInflater)
        setContentView(binding.root)

        mAuth = FirebaseAuth.getInstance()

        val user = mAuth.currentUser
        binding.userName.text = user?.displayName
        binding.userEmail.text = user?.email
        val photoUrl = user?.photoUrl.toString()
        Log.w("URL", photoUrl)
        Glide.with(this).load(photoUrl).into(binding.profileImage)

        binding.logoutBtn.setOnClickListener {
            mAuth.signOut()
            startActivity(Intent(this, MainActivity::class.java))
            finish()
        }
    }
}