package com.terry.login_kotlin

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity



@SuppressLint("Registered")
class SuccessActivity: AppCompatActivity() {

    private var mExit:Button? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_success)
        init()
    }

    private fun init() {
        mExit = findViewById<View>(R.id.log_exit_button) as Button

        mExit!!.setOnClickListener {
            val intent = Intent(this@SuccessActivity,LoginActivity::class.java)
            startActivity(intent)
        }
    }

}