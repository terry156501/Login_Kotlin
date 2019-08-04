package com.terry.login_kotlin.Model

import com.terry.login_kotlin.Presenter.OnLoginFinishedListener

interface LoginModel {
    fun login(username: String, password: String, listener: OnLoginFinishedListener)

}
