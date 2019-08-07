package com.terry.login_kotlin.presenter

import android.annotation.SuppressLint
import android.app.Activity
import android.content.Context
import android.content.Intent
import android.text.TextUtils
import android.widget.EditText
import com.terry.login_kotlin.MD5Utils
import com.terry.login_kotlin.contract.IRegisterTestContract
import com.terry.login_kotlin.view.RegisterTestActivity
import java.util.regex.Pattern

/**
 * @describe
 * @author  Terry
 * @date 2019/8/7  20:16
 * 								 - generate by MvpAutoCodePlus plugin.
 */

@SuppressLint("Registered")
class RegisterTestPresenter(private var registerView:RegisterTestActivity) : BasePresenterImpl<IRegisterTestContract.View, IRegisterTestContract.Model>(){
    fun onEmptyUser()
    {
        registerView.setEmptyUser()
    }

    fun onEmptyPhone(){
        registerView.setEmptyPhone()
    }

    fun onWrongPhone(){
        registerView.setWrongPhone()
    }

    fun onEmptyPsw_01() {
        registerView.setEmptyPsw_01()
    }

    fun onEmptyPsw_02(){
        registerView.setEmptyPsw_02()
    }

    fun onWrongPsw() {
        registerView.setWrongPsw()
    }

    fun onPswError() {
        registerView.setPswError()
    }

    fun onExistUser() {
        registerView.setExistUser()
    }

    fun onSuccess() {
        registerView.setSuccess()
    }

    fun checkPhoneNum(num: String): Boolean{
        val regExp = "^((13[0-9])|(14[5,7,9])|(15[^4])|(18[0-9])|(17[0,1,3,5,6,7,8]))\\d{8}\$"
        val p = Pattern.compile(regExp)
        val m = p.matcher(num)
        return m.matches()
    }

    fun register(username:String, phone: EditText, password_01:String, password_02:String, context: Context)
    {
        val regPsw = Regex("[a-z,0-9]{6,16}")
        if(TextUtils.isEmpty(username)){
            onEmptyUser()
        }
        else if(TextUtils.isEmpty(phone.toString())){
            onEmptyPhone()
        }
        else if(!checkPhoneNum(phone.toString())){
            onWrongPhone()
        }
        else if(TextUtils.isEmpty(password_01)){
            onEmptyPsw_01()
        }
        else if(TextUtils.isEmpty(password_02)){
            onEmptyPsw_02()
        }
        else if(password_01!=password_02){
            onWrongPsw()
        }
        else if(!regPsw.matches(password_01)){
            onPswError()
        }
        else if(isExistUserName(username,context)){
            onExistUser()
        }
        else{
            saveRegisterInfo(username,password_01,context)
            val data = Intent()
            data.putExtra("username",username)
            setResult(Activity.RESULT_OK,data)
            onSuccess()
        }
    }
    private fun isExistUserName(userName: String?,context: Context): Boolean {
        var has_userName = false
        // "loginInfo"
        val sp = context.getSharedPreferences("loginInfo", MODE_PRIVATE)
        //获取密码
        val spPsw = sp.getString(userName, "")//传入用户名获取密码
        //如果密码不为空则确实保存过这个用户名
        if (!TextUtils.isEmpty(spPsw)) {
            has_userName = true
        }
        return has_userName
    }
    private fun saveRegisterInfo(userName: String?, psw: String?,context: Context) {
        //把密码用MD5加密
        val md5Psw = MD5Utils.md5(psw!!)
        //loginInfo表示文件名
        val sp = context.getSharedPreferences("loginInfo", MODE_PRIVATE)
        val editor = sp.edit()
        //以用户名为key，密码为value保存在SharedPreferences中
        //key,value
        editor.putString(userName, md5Psw)
        editor.apply()
    }
}

