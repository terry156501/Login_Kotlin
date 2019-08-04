package com.terry.login_kotlin.presenter

interface IPresenter {
    fun onEmptyUser()
    fun onEmptyPsw()
    fun onWrongPsw()
    fun onUnExUser()
    fun onPswError()
    fun onSuccess()

    fun login(username:String,psw:String)
}