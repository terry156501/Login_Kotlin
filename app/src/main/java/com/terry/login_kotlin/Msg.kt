package com.terry.login_kotlin


class Msg(val content: String, val type: Int) {
    companion object {
        val TYPE_RECEIVED = 0
        val TYPE_SEND = 1
    }
}
