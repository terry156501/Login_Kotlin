package com.terry.login_kotlin.Presenter

interface OnLoginFinishedListener {
    fun onEmptyUser()
    fun onEmptyPsw()
    fun onWrongPsw()
    fun onUnExUser()
    fun onPswError()
    fun onSuccess()
}