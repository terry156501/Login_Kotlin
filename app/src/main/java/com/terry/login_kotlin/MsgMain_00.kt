package com.terry.login_kotlin

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import android.widget.ListView

import java.util.ArrayList

class MsgMain_00 : AppCompatActivity() {

    private var msgListView: ListView? = null
    private var inputText: EditText? = null
    private var send: Button? = null
    private var adapter: MsgAdapter? = null
    private var mBack: ImageView? = null

    private val msgList = ArrayList<Msg>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)


        val actionBar = supportActionBar
        actionBar!!.hide()

        setContentView(R.layout.message)

        initMsgs()
        adapter = MsgAdapter(this@MsgMain_00, R.layout.msg_send, msgList as List<Msg>)
        inputText = findViewById(R.id.input_text)
        send = findViewById(R.id.send)
        msgListView = findViewById(R.id.msg_list_view)
        mBack = findViewById(R.id.back)
        msgListView!!.adapter = adapter

        mBack!!.setOnClickListener{
            val intent = Intent(this@MsgMain_00,MainActivity::class.java)
            intent.putExtra("Fragment","0")
            startActivity(intent)
        }

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