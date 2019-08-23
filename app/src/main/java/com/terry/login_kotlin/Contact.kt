package com.terry.login_kotlin

import java.io.Serializable

class Contact(private val mName: String, private val mType: Int) : Serializable {

    fun getmName(): String {
        return mName
    }

    fun getmType(): Int {
        return mType
    }

}
