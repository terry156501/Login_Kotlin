package com.terry.login_kotlin

import net.sourceforge.pinyin4j.PinyinHelper
import net.sourceforge.pinyin4j.format.HanyuPinyinCaseType
import net.sourceforge.pinyin4j.format.HanyuPinyinOutputFormat
import net.sourceforge.pinyin4j.format.HanyuPinyinToneType
import net.sourceforge.pinyin4j.format.HanyuPinyinVCharType
import net.sourceforge.pinyin4j.format.exception.BadHanyuPinyinOutputFormatCombination


object Utils {
    fun getPingYin(inputString: String): String {
        val format = HanyuPinyinOutputFormat()
        format.caseType = HanyuPinyinCaseType.LOWERCASE
        format.toneType = HanyuPinyinToneType.WITHOUT_TONE
        format.vCharType = HanyuPinyinVCharType.WITH_V
        val input = inputString.trim { it <= ' ' }.toCharArray()
        var output = ""
        try {
            for (char in input) {
                output += if (char.toString().matches("[\\u4E00-\\u9FA5]+".toRegex())) {
                    val temp = PinyinHelper.toHanyuPinyinStringArray(char, format)
                    temp[0]
                } else {
                    char.toString()
                }
            }
        } catch (e: BadHanyuPinyinOutputFormatCombination) {
            e.printStackTrace()
        }

        return output
    }
}