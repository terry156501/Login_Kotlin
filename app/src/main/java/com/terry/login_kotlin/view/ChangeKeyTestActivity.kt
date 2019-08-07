package com.terry.login_kotlin.view

import android.content.Intent
import android.os.Bundle
import android.os.PersistableBundle
import android.view.View
import android.widget.Toast
import com.terry.login_kotlin.R
import com.terry.login_kotlin.contract.IChangeKeyTestContract
import com.terry.login_kotlin.act.BaseMVPActivity
import com.terry.login_kotlin.presenter.ChangeKeyTestPresenter
import com.terry.login_kotlin.presenter.RegisterTestPresenter
import kotlinx.android.synthetic.main.activity_changekey.*
import kotlinx.android.synthetic.main.activity_changekey.view.*

/**
 * @describe
 * @author  Terry
 * @date 2019/8/7  21:12
 * 								 - generate by MvpAutoCodePlus plugin.
 */

class ChangeKeyTestActivity : BaseMVPActivity<IChangeKeyTestContract.View, IChangeKeyTestContract.Presenter>(),
    IChangeKeyTestContract.View, View.OnClickListener {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_changekey)
        change_affirm_button.setOnClickListener(this)
        change_back_button.setOnClickListener(this)
    }

    override fun onClick(p0: View?) {
        var presenter = ChangeKeyTestPresenter(this)
        when(p0?.id)
        {
            R.id.change_affirm_button -> presenter.changeKey(input_change_id.text.toString(),input_change_oldkey.text.toString(),input_change_oldkey.text.toString(),this)
            R.id.change_back_button -> setGoBack()
        }
    }

    fun setEmptyUser() {
        Toast.makeText(this@ChangeKeyTestActivity, "请输入用户名", Toast.LENGTH_SHORT).show()
    }

    fun setEmptyPswOld() {
        Toast.makeText(this@ChangeKeyTestActivity, "请输入旧密码", Toast.LENGTH_SHORT).show()
    }

    fun setEmptyPswNew(){
        Toast.makeText(this@ChangeKeyTestActivity, "请输入新密码", Toast.LENGTH_SHORT).show()
    }
    fun setWrongPsw() {
        Toast.makeText(this@ChangeKeyTestActivity, "输入的用户名和密码不一致", Toast.LENGTH_SHORT).show()
    }

    fun setPswError() {
        Toast.makeText(this@ChangeKeyTestActivity, "密码格式错误", Toast.LENGTH_SHORT).show()
    }

    fun setUnExUser() {
        Toast.makeText(this@ChangeKeyTestActivity, "此用户名不存在", Toast.LENGTH_SHORT).show()
    }

    fun setSuccess() {
        Toast.makeText(this@ChangeKeyTestActivity, "修改成功", Toast.LENGTH_SHORT).show()
        this@ChangeKeyTestActivity.finish()
        val intent = Intent(this@ChangeKeyTestActivity, LoginTestActivity::class.java)
        startActivity(intent)
    }

    fun setGoBack(){

        this@ChangeKeyTestActivity.finish()
        val intent = Intent(this@ChangeKeyTestActivity, LoginTestActivity::class.java)
        startActivity(intent)
    }
}

