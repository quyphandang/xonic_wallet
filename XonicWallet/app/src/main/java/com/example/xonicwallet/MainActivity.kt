package com.example.xonicwallet

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_main.*
import android.content.Intent

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        importwallet.setOnClickListener {
            val intent:  Intent = Intent(this, ImportWallet::class.java)
            startActivity(intent)
        }
    }
}
