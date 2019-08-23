package com.terry.login_kotlin

import android.content.Context
import android.util.AttributeSet
import android.view.Gravity
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView

class LinkManList(private var mContext:Context,attrs: AttributeSet):LinearLayout(mContext,attrs){
    private var mListener:CharacterClickListener? = null
    init {
        mContext = context
        orientation = VERTICAL
        initView()
    }

    private fun initView() {
        addView(buildImageLayout())

        var i = 'A'
        while (i <= 'Z') {
            val character = i + ""
            val tv = buildTextLayout(character)

            addView(tv)
            i++
        }
        addView(buildTextLayout("#"))
    }

    private fun buildTextLayout(character: String): TextView {
        val layoutParams = LayoutParams(
            LayoutParams.MATCH_PARENT,
            LayoutParams.MATCH_PARENT,
            1f
        )

        val tv = TextView(mContext)
        tv.layoutParams = layoutParams
        tv.gravity = Gravity.CENTER
        tv.isClickable = true

        tv.text = character

        tv.setOnClickListener {
            if (mListener != null) {
                mListener!!.clickCharacter(character)
            }
        }
        return tv
    }

    private fun buildImageLayout(): ImageView {
        val layoutParams = LayoutParams(
            LayoutParams.MATCH_PARENT,
            LayoutParams.MATCH_PARENT,
            1f
        )

        val iv = ImageView(mContext)
        iv.setBackgroundResource(R.drawable.arrow)
        iv.layoutParams = layoutParams
        iv.setOnClickListener {
            if (mListener != null) {
                mListener!!.clickArrow()
            }
        }
        return iv
    }

    fun setCharacterListener(listener: CharacterClickListener) {
        mListener = listener
    }

    interface CharacterClickListener {
        fun clickCharacter(character: String)

        fun clickArrow()
    }
}
