package com.terry.login_kotlin.view

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Toast
import com.terry.login_kotlin.R
import com.terry.login_kotlin.contract.IRegisterTestContract
import com.terry.login_kotlin.act.BaseMVPActivity
import com.terry.login_kotlin.presenter.RegisterTestPresenter
import kotlinx.android.synthetic.main.activity_register.*

/**
 * @describe
 * @author  Terry
 * @date 2019/8/7  20:16
 * 								 - generate by MvpAutoCodePlus plugin.
 */

class RegisterTestActivity : BaseMVPActivity<IRegisterTestContract.View, IRegisterTestContract.Presenter>(),
    IRegisterTestContract.View, View.OnClickListener {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)
        register_button.setOnClickListener(this)
        back_button.setOnClickListener(this)
    }

    override fun onClick(p0: View?) {
        val presenter = RegisterTestPresenter(this)
        when(p0?.id)
        {
            R.id.back_button -> setBackToLogin()
            R.id.register_button -> presenter.register(input_identity_text.text.toString(),input_phone_text,input_password_text.text.toString(),input_password02_text.text.toString(),this)
        }
    }
    fun setEmptyUser() {
        Toast.makeText(this@RegisterTestActivity, "请输入用户名", Toast.LENGTH_SHORT).show()
    }
    fun setEmptyPhone(){
        Toast.makeText(this@RegisterTestActivity, "请输入手机号", Toast.LENGTH_SHORT).show()
    }
    fun setWrongPhone(){
        Toast.makeText(this@RegisterTestActivity, "请输入正确的手机号", Toast.LENGTH_SHORT).show()
    }
    fun setEmptyPsw_01() {
        Toast.makeText(this@RegisterTestActivity, "请输入密码", Toast.LENGTH_SHORT).show()
    }
    fun setEmptyPsw_02() {
        Toast.makeText(this@RegisterTestActivity, "请再次输入密码", Toast.LENGTH_SHORT).show()
    }

    fun setWrongPsw() {
        Toast.makeText(this@RegisterTestActivity, "输入两次的密码不一样", Toast.LENGTH_SHORT).show()
    }

    fun setPswError() {
        Toast.makeText(this@RegisterTestActivity, "密码格式错误", Toast.LENGTH_SHORT).show()
    }

    fun setExistUser() {
        Toast.makeText(this@RegisterTestActivity, "此用户名已存在", Toast.LENGTH_SHORT).show()
    }

    fun setSuccess(){
        Toast.makeText(this@RegisterTestActivity, "注册成功", Toast.LENGTH_SHORT).show()
        this@RegisterTestActivity.finish()
        val intent = Intent(this@RegisterTestActivity, LoginTestActivity::class.java)
        startActivity(intent)
    }

    fun setBackToLogin(){
        this@RegisterTestActivity.finish()
        val intent = Intent(this@RegisterTestActivity, LoginTestActivity::class.java)
        startActivity(intent)
    }
}

