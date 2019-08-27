package com.terry.login_kotlin

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import com.terry.login_kotlin.Fragments.Fragment_03

class Rich_igon_01Activity:AppCompatActivity() {
    private var mButton:Button? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.rich_igon_01)
        mButton = findViewById(R.id.rich_igon_01_button)
        mButton!!.setOnClickListener{
            val intent = Intent(this@Rich_igon_01Activity, MainActivity::class.java)
            startActivity(intent)
        }
    }
}