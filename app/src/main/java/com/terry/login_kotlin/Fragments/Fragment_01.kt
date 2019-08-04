package com.terry.login_kotlin.Fragments

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import com.terry.login_kotlin.MsgMain
import com.terry.login_kotlin.R

class Fragment_01 : Fragment() {
    private var textView: TextView? = null
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_01, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        textView = activity!!.findViewById(R.id.msg_f) as TextView
        textView!!.setOnClickListener {
            val intent = Intent(activity, MsgMain::class.java)
            startActivity(intent)
        }
    }

}