package com.terry.login_kotlin

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView

class MsgAdapter(context: Context, private val resourceId: Int, objects: List<Msg>) :
    ArrayAdapter<Msg>(context, resourceId, objects) {

    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        val msg = getItem(position)
        val view: View
        val viewHolder: ViewHolder
        if (convertView == null) {
            view = LayoutInflater.from(context).inflate(resourceId, null)
            viewHolder = ViewHolder()
            viewHolder.leftLayout = view.findViewById<View>(R.id.left_layout) as LinearLayout
            viewHolder.rightLayout = view.findViewById<View>(R.id.right_layout) as LinearLayout
            viewHolder.leftMsg = view.findViewById<View>(R.id.left_msg) as TextView
            viewHolder.rightMsg = view.findViewById<View>(R.id.right_msg) as TextView
            viewHolder.head1 = view.findViewById<View>(R.id.head_01) as ImageView
            viewHolder.head2 = view.findViewById<View>(R.id.head_02) as ImageView
            view.tag = viewHolder
        } else {
            view = convertView
            viewHolder = view.tag as ViewHolder
        }
        if (msg!!.type == Msg.TYPE_RECEIVED) {
            viewHolder.leftLayout!!.visibility = View.VISIBLE
            viewHolder.head1!!.visibility = View.VISIBLE
            viewHolder.rightLayout!!.visibility = View.GONE
            viewHolder.head2!!.visibility = View.GONE
            viewHolder.leftMsg!!.text = msg.content
        } else if (msg.type == Msg.TYPE_SEND) {
            viewHolder.rightLayout!!.visibility = View.VISIBLE
            viewHolder.head2!!.visibility = View.VISIBLE
            viewHolder.leftLayout!!.visibility = View.GONE
            viewHolder.head1!!.visibility = View.GONE
            viewHolder.rightMsg!!.text = msg.content
        }
        return view
    }

    internal inner class ViewHolder {
        var leftLayout: LinearLayout? = null
        var rightLayout: LinearLayout? = null
        var leftMsg: TextView? = null
        var rightMsg: TextView? = null
        var head1: ImageView? = null
        var head2: ImageView? = null
    }
}