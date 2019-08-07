package com.terry.login_kotlin.model

import android.annotation.SuppressLint
import android.app.Activity
import android.content.Context
import android.content.Intent
import android.text.TextUtils
import com.terry.login_kotlin.MD5Utils
import com.terry.login_kotlin.presenter.LoginTestPresenter
import kotlinx.android.synthetic.main.activity_login.*

/**
 * @describe
 * @author  Terry
 * @date 2019/8/3  16:02
 * 								 - generate by MvpAutoCodePlus plugin.
 */

@SuppressLint("Registered")
class LoginTestModel : BaseModelImpl(){

//    fun checkPsw(username: String, password: String,context: Context) {
//        val regPsw = Regex("[A-Z,a-z,0-9,?,!,(,)]{6,16}")
//        val md5Psw = MD5Utils.md5(password)
//        val spPsw = readPsw(username,context)
//        if(TextUtils.isEmpty(username))
//        {
//            onEmptyUser()
//        }
//        else if(TextUtils.isEmpty(password))
//        {
//            onEmptyPsw()
//        }
//        else if(md5Psw == spPsw)
//        {
//            saveLoginStatus(true, username,context)
//            val data = Intent()
//            data.putExtra("isLogin", true)
//            setResult(Activity.RESULT_OK, data)
//            onSuccess()
//        }
//        else if(spPsw != null && !TextUtils.isEmpty(spPsw) && md5Psw != spPsw)
//        {
//            onWrongPsw()
//        }
//        else if(!regPsw.matches(password))
//        {
//            onPswError()
//        }
//        else
//        {
//            onUnExUser()
//        }
//
//    }
//
//    private fun readPsw(username: String?, context: Context): String? {
//        val sp = context.getSharedPreferences("loginInfo", MODE_PRIVATE)
//        return sp.getString(username,"")
//    }
//
//    private fun saveLoginStatus(status: Boolean, userName: String?, context: Context) {
//        val sp = context.getSharedPreferences("loginInfo", MODE_PRIVATE)
//        val editor = sp.edit()
//        editor.putBoolean("isLogin", status)
//        //存入登录状态时的用户名
//        editor.putString("loginUserName", userName)
//        editor.apply()
//    }
//
//    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
//        super.onActivityResult(requestCode, resultCode, data)
//        if (data != null) {
//            //是获取注册界面回传过来的用户名
//            // getExtra().getString("***");
//            val userName = data.getStringExtra("userName")
//            if (!TextUtils.isEmpty(userName)) {
//                //设置用户名到 input_user_text 控件
//                input_user_text!!.setText(userName)
//                //input_user_text控件的setSelection()方法来设置光标位置
//                input_user_text!!.setSelection(userName!!.length)
//            }
//        }
//    }
}

