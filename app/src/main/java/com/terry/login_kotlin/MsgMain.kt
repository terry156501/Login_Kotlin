package com.terry.login_kotlin

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.ListView

import java.util.ArrayList

class MsgMain : AppCompatActivity() {

    private var msgListView: ListView? = null
    private var inputText: EditText? = null
    private var send: Button? = null
    private var adapter: MsgAdapter? = null

    private val msgList = ArrayList<Msg>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val actionBar = supportActionBar
        actionBar!!.hide()

        setContentView(R.layout.message)

        initMsgs()
        adapter = MsgAdapter(this@MsgMain, R.layout.msg_send, msgList as List<Msg>)
        inputText = findViewById<EditText>(R.id.input_text)
        send = findViewById<Button>(R.id.send)
        msgListView = findViewById<ListView>(R.id.msg_list_view)
        msgListView!!.adapter = adapter
        send!!.setOnClickListener {
            val content = inputText!!.text.toString()
            if ("" != content) {
                val msg = Msg(content, Msg.TYPE_SEND)
                msgList.add(msg)
                adapter!!.notifyDataSetChanged()
                msgListView!!.setSelection(msgList.size)
                inputText!!.setText("")
            }
        }
    }

    private fun initMsgs() {
        val msg1 = Msg("Hello, how are you?", Msg.TYPE_RECEIVED)
        msgList.add(msg1)
        val msg2 = Msg("Fine, thank you, and you?", Msg.TYPE_SEND)
        msgList.add(msg2)
        val msg3 = Msg("I am fine, too!", Msg.TYPE_RECEIVED)
        msgList.add(msg3)
    }
}