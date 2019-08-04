package com.terry.login_kotlin.presenter

import android.annotation.SuppressLint
import com.terry.login_kotlin.contract.ILoginTestContract
import com.terry.login_kotlin.model.LoginTestModel
import com.terry.login_kotlin.view.LoginTestActivity

/**
 * @describe
 * @author  Terry
 * @date 2019/8/3  16:02
 * 								 - generate by MvpAutoCodePlus plugin.
 */

@SuppressLint("Registered")
class LoginTestPresenter(private var loginView:LoginTestActivity) : BasePresenterImpl<ILoginTestContract.View, ILoginTestContract.Model>(),
    ILoginTestContract.Presenter {
    private var model:LoginTestModel? = null

    override fun onEmptyUser() {
        loginView.setEmptyUser()
    }

    override fun onEmptyPsw() {
        loginView.setEmptyPsw()
    }

    override fun onPswError() {
        loginView.setPswError()
    }

    override fun onUnExUser() {
        loginView.setUnExUser()
    }

    override fun onWrongPsw() {
        loginView.setWrongPsw()
    }

    override fun onSuccess() {
        loginView.Success()
    }

    override fun login(username:String,psw:String) {
        model = LoginTestModel()
        model?.checkPsw(username,psw)
    }

}

