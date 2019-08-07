package com.terry.login_kotlin.view

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Toast
import com.terry.login_kotlin.R
import com.terry.login_kotlin.SuccessActivity
import com.terry.login_kotlin.contract.ILoginTestContract
import com.terry.login_kotlin.act.BaseMVPActivity
import com.terry.login_kotlin.presenter.LoginTestPresenter
import kotlinx.android.synthetic.main.activity_login.*

/**
 * @describe
 * @author  Terry
 * @date 2019/8/3  16:02
 * 								 - generate by MvpAutoCodePlus plugin.
 */

class LoginTestActivity : BaseMVPActivity<ILoginTestContract.View, ILoginTestContract.Presenter>(),
    ILoginTestContract.View, View.OnClickListener {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        login_button.setOnClickListener(this)
        register_button.setOnClickListener(this)
        change_button.setOnClickListener(this)
    }

    override fun onClick(p0: View?) {
        var presenter = LoginTestPresenter(this)
        when(p0?.id)
        {
            R.id.login_button -> presenter.login(input_user_text.text.toString(),input_key_text.text.toString(),this)
            R.id.register_button -> Reg()
            R.id.change_button -> Change()
        }
    }


    fun setEmptyUser() {
        Toast.makeText(this@LoginTestActivity, "请输入用户名", Toast.LENGTH_SHORT).show()
    }

    fun setEmptyPsw() {
        Toast.makeText(this@LoginTestActivity, "请输入密码", Toast.LENGTH_SHORT).show()
    }

    fun setWrongPsw() {
        Toast.makeText(this@LoginTestActivity, "输入的用户名和密码不一致", Toast.LENGTH_SHORT).show()
    }

    fun setUnExUser() {
        Toast.makeText(this@LoginTestActivity, "此用户名不存在", Toast.LENGTH_SHORT).show()
    }

    fun setPswError() {
        Toast.makeText(this@LoginTestActivity, "密码格式错误", Toast.LENGTH_SHORT).show()
    }

    fun Success() {
        this@LoginTestActivity.finish()
        var intent = Intent(this@LoginTestActivity, SuccessActivity::class.java)
        startActivity(intent)
    }

    fun Reg() {
        this@LoginTestActivity.finish()
        val intent = Intent(this@LoginTestActivity, RegisterTestActivity::class.java)
        startActivity(intent)
    }

    fun Change() {
        this@LoginTestActivity.finish()
        val intent = Intent(this@LoginTestActivity,ChangeKeyTestActivity::class.java)
        startActivity(intent)
    }

}