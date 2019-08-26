package com.terry.login_kotlin

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import com.terry.login_kotlin.Fragments.Fragment_03

class Rich_01Activity:AppCompatActivity() {
    private var mButton:Button? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.page_03)
        mButton = findViewById(R.id.rich_01_button)
        mButton!!.setOnClickListener{
            val intent = Intent(this@Rich_01Activity, Fragment_03::class.java)
            startActivity(intent)
        }
    }
}