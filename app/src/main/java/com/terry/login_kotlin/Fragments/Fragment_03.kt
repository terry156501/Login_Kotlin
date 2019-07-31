package com.terry.login_kotlin

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.fragment.app.Fragment


class Fragment_03 : Fragment() {
    private var textView: TextView? = null
    private var mbutton: Button? = null
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_03, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        textView = activity!!.findViewById(R.id.textView1) as TextView
        mbutton = activity!!.findViewById(R.id.log_off) as Button
        mbutton!!.setOnClickListener {
            val intent = Intent(activity,LoginActivity::class.java)
            startActivity(intent)
        }
    }
}