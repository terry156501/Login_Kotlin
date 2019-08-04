package com.terry.login_kotlin.view


interface IView {
    fun setEmptyUser()
    fun setEmptyPsw()
    fun setWrongPsw()
    fun setUnExUser()
    fun setPswError()
    fun Success()

    fun login()
    fun Reg()
    fun Change()
}