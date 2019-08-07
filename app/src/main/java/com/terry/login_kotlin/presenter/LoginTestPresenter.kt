package com.terry.login_kotlin.presenter

import android.annotation.SuppressLint
import android.app.Activity
import android.content.Context
import android.content.Intent
import android.text.TextUtils
import com.terry.login_kotlin.MD5Utils
import com.terry.login_kotlin.contract.ILoginTestContract
import com.terry.login_kotlin.view.LoginTestActivity

/**
 * @describe
 * @author  Terry
 * @date 2019/8/3  16:02
 * 								 - generate by MvpAutoCodePlus plugin.
 */

@SuppressLint("Registered")
class LoginTestPresenter(private var loginView:LoginTestActivity) : BasePresenterImpl<ILoginTestContract.View, ILoginTestContract.Model>() {


    fun onEmptyUser() {
        loginView.setEmptyUser()
    }

    fun onEmptyPsw() {
        loginView.setEmptyPsw()
    }

    fun onPswError() {
        loginView.setPswError()
    }

    fun onUnExUser() {
        loginView.setUnExUser()
    }

    fun onWrongPsw() {
        loginView.setWrongPsw()
    }

    fun onSuccess() {
        loginView.Success()
    }

    fun login(username:String,psw:String,context: Context) {
        val regPsw = Regex("[a-z,0-9]{6,16}")
        val md5Psw = MD5Utils.md5(psw)
        val spPsw = readPsw(username,context)
        if(TextUtils.isEmpty(username))
        {
            onEmptyUser()
        }
        else if(TextUtils.isEmpty(psw))
        {
            onEmptyPsw()
        }
        else if(md5Psw == spPsw)
        {
            saveLoginStatus(true, username,context)
            val data = Intent()
            data.putExtra("isLogin", true)
            setResult(Activity.RESULT_OK, data)
            onSuccess()
        }
        else if(spPsw != null && !TextUtils.isEmpty(spPsw) && md5Psw != spPsw)
        {
            onWrongPsw()
        }
        else if(!regPsw.matches(psw))
        {
            onPswError()
        }
        else
        {
            onUnExUser()
        }
    }

    private fun readPsw(username: String?,context: Context): String? {
        val sp = context.getSharedPreferences("loginInfo", MODE_PRIVATE)
        return sp.getString(username,"")
    }

    private fun saveLoginStatus(status: Boolean, userName: String?,context: Context) {
        val sp = context.getSharedPreferences("loginInfo", MODE_PRIVATE)
        val editor = sp.edit()
        editor.putBoolean("isLogin", status)
        //存入登录状态时的用户名
        editor.putString("loginUserName", userName)
        editor.apply()
    }

}

