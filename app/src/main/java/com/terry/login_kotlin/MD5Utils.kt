package com.terry.login_kotlin

import java.security.MessageDigest
import java.security.NoSuchAlgorithmException
import kotlin.experimental.and

object MD5Utils {
    //md5 加密算法
    fun md5(text: String): String {
        val digest: MessageDigest?
        try {
            digest = MessageDigest.getInstance("md5")
            // 数组 byte[] result -> digest.digest( );  文本 text.getBytes();
            val result = digest!!.digest(text.toByteArray())
            val sb = StringBuffer()
            // result数组，digest.digest ( ); -> text.getBytes();
            for (b in result) {
                // 0xff 为16进制
                val number = b and 0xff.toByte()
                // number值 转换 字符串 Integer.toHexString( );
                val hex = Integer.toHexString(number.toInt())
                if (hex.length == 1) {
                    sb.append("0$hex")
                } else {
                    sb.append(hex)
                }
            }
            return sb.toString()
        } catch (e: NoSuchAlgorithmException) {
            e.printStackTrace()
            //发送异常return空字符串
            return ""
        }

    }
}