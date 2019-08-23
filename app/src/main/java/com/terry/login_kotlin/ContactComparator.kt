package com.terry.login_kotlin

import java.util.Comparator


class ContactComparator : Comparator<String> {

    override fun compare(o1: String, o2: String): Int {
        val c1 = (o1[0] + "").toUpperCase().hashCode()
        val c2 = (o2[0] + "").toUpperCase().hashCode()

        val c1Flag = c1 < "A".hashCode() || c1 > "Z".hashCode() // 不是字母
        val c2Flag = c2 < "A".hashCode() || c2 > "Z".hashCode() // 不是字母
        if (c1Flag && !c2Flag) {
            return 1
        } else if (!c1Flag && c2Flag) {
            return -1
        }

        return c1 - c2
    }

}
