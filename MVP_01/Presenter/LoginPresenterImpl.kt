package com.terry.login_kotlin.Presenter

import com.terry.login_kotlin.Model.LoginModel
import com.terry.login_kotlin.Model.LoginModelImpl
import com.terry.login_kotlin.View.LoginView

class LoginPresenterImpl(private var loginView: LoginView?):LoginPresenter, OnLoginFinishedListener {
    private val logModel:LoginModel
    init {
        this.logModel = LoginModelImpl()
    }

    override fun checkUserPsw(username: String, password: String) {
        logModel.login(username,password,this)
    }



    override fun onEmptyUser() {
        loginView?.setEmptyUser()
    }

    override fun onEmptyPsw() {
        loginView?.setEmptyPsw()
    }

    override fun onPswError() {
        loginView?.setPswError()
    }

    override fun onUnExUser() {
        loginView?.setUnExUser()
    }

    override fun onWrongPsw() {
        loginView?.setWrongPsw()
    }

    override fun onSuccess() {
        loginView?.Success()
    }
}