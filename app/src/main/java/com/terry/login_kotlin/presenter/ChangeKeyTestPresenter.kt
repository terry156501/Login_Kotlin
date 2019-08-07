package com.terry.login_kotlin.presenter

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.text.TextUtils
import com.terry.login_kotlin.MD5Utils
import com.terry.login_kotlin.contract.IChangeKeyTestContract
import com.terry.login_kotlin.presenter.BasePresenterImpl
import com.terry.login_kotlin.view.ChangeKeyTestActivity

/**
 * @describe
 * @author  Terry
 * @date 2019/8/7  21:12
 * 								 - generate by MvpAutoCodePlus plugin.
 */

class ChangeKeyTestPresenter(private var changeKeyView:ChangeKeyTestActivity) : BasePresenterImpl<IChangeKeyTestContract.View, IChangeKeyTestContract.Model>(),
    IChangeKeyTestContract.Presenter {
    fun onEmptyUser() {
        changeKeyView.setEmptyUser()
    }

    fun onEmptyPswOld() {
        changeKeyView.setEmptyPswOld()
    }

    fun onEmptyPswNew(){
        changeKeyView.setEmptyPswNew()
    }

    fun onWrongPsw() {
        changeKeyView.setWrongPsw()
    }

    fun onPswError() {
        changeKeyView.setPswError()
    }

    fun onUnExistUser()
    {
        changeKeyView.setUnExUser()
    }

    fun onSuccess()
    {
        changeKeyView.setSuccess()
    }

    fun changeKey(username:String,passwordOld:String,passwordNew:String,context: Context)
    {
        val md5Psw = MD5Utils.md5(passwordOld)
        val oldKey = readPsw(username,context)
        val regPsw = Regex("[A-Z,a-z,0-9,?,!,(,)]{6,16}")
        if(TextUtils.isEmpty(username)){
            onEmptyUser()
        }
        else if(TextUtils.isEmpty(passwordOld)){
            onEmptyPswOld()
        }
        else if(TextUtils.isEmpty(passwordNew)){
            onEmptyPswNew()
        }
        else if(!TextUtils.isEmpty(passwordOld) && md5Psw != oldKey){
            onWrongPsw()
        }
        else if(!regPsw.matches(passwordNew)){
            onPswError()
        }
        else if(md5Psw == oldKey){
            saveChangeInfo(username, passwordNew,context)
            val data = Intent()
            data.putExtra("userName", username)
            setResult(Activity.RESULT_OK, data)
            onSuccess()
        }
        else{
            onUnExistUser()
        }
    }
    private fun readPsw(userName: String?,context: Context): String? {
        val sp = context.getSharedPreferences("loginInfo", MODE_PRIVATE)
        return sp.getString(userName, "")
    }
    private fun saveChangeInfo(userName: String?, key: String?,context: Context) {
        val md5Psw = MD5Utils.md5(key!!)
        val sp = context.getSharedPreferences("loginInfo", MODE_PRIVATE)
        val editor = sp.edit()
        //改变密码
        editor.putString(userName, md5Psw)
        editor.apply()
    }
}

