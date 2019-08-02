package com.terry.login_kotlin

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import com.terry.login_kotlin.Presenter.LoginPresenter
import com.terry.login_kotlin.Presenter.LoginPresenterImpl
import com.terry.login_kotlin.View.LoginView
import kotlinx.android.synthetic.main.activity_login.*


class LoginActivity : AppCompatActivity(), LoginView, View.OnClickListener{


    private lateinit var presenter:LoginPresenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        login_button.setOnClickListener(this)
        presenter = LoginPresenterImpl(this)
    }

    override fun setEmptyPsw() {
        Toast.makeText(this@LoginActivity, "请输入用户名", Toast.LENGTH_SHORT).show()
    }

    override fun setEmptyUser() {
        Toast.makeText(this@LoginActivity, "请输入密码", Toast.LENGTH_SHORT).show()
    }

    override fun setPswError() {
        Toast.makeText(this@LoginActivity, "输入的用户名和密码不一致", Toast.LENGTH_SHORT).show()
    }

    override fun setUnExUser() {
        Toast.makeText(this@LoginActivity, "此用户名不存在", Toast.LENGTH_SHORT).show()
    }

    override fun setWrongPsw() {
        Toast.makeText(this@LoginActivity, "密码格式错误", Toast.LENGTH_SHORT).show()
    }

    override fun Success() {
        Toast.makeText(this@LoginActivity, "登录成功", Toast.LENGTH_SHORT).show()
        val data = Intent()
        data.putExtra("isLogin", true)
        setResult(Activity.RESULT_OK, data)
        this@LoginActivity.finish()
        startActivity(Intent(this@LoginActivity, SuccessActivity::class.java))
    }
    override fun onClick(p0: View?) {
        presenter.checkUserPsw(input_user_text.text.toString(),input_key_text.toString())
    }
}


