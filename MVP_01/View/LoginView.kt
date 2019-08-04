package com.terry.login_kotlin.View

import android.content.Intent

interface LoginView {
    fun setEmptyUser()
    fun setEmptyPsw()
    fun setWrongPsw()
    fun setUnExUser()
    fun setPswError()
    fun Success()

    fun Reg()
    fun Change()

}