package com.terry.login_kotlin

import android.content.Intent
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.terry.login_kotlin.ChatItemViewBinder.ViewHolder
import me.drakeet.multitype.ItemViewBinder

class ChatItemViewBinder:ItemViewBinder<ChatItem,ViewHolder>() {

    override fun onCreateViewHolder(inflater: LayoutInflater, parent: ViewGroup): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.fragment_01_item, parent, false)
        val holder = ViewHolder(view)
        holder.text.setOnClickListener {
            val intent = Intent(it.context, MsgMain_00::class.java)
            it.context.startActivity(intent)
        }
        holder.img.setOnClickListener {
            val intent = Intent(it.context, MsgMain_00::class.java)
            it.context.startActivity(intent)
        }
        return holder
    }

    override fun onBindViewHolder(holder: ViewHolder, item: ChatItem) {
        holder.text.text = item.name
        Log.d("ItemViewBinder API", "position: ${getPosition(holder)}")
        Log.d("ItemViewBinder API", "items: $adapterItems")
        Log.d("ItemViewBinder API", "adapter: $adapter")
        Log.d("More", "Context: ${holder.itemView.context}")
        holder.img.setImageResource(item.img)
        Log.d("ItemViewBinder API", "position: ${getPosition(holder)}")
        Log.d("ItemViewBinder API", "items: $adapterItems")
        Log.d("ItemViewBinder API", "adapter: $adapter")
        Log.d("More", "Context: ${holder.itemView.context}")
    }

    class ViewHolder(itemView : View): RecyclerView.ViewHolder(itemView) {
        val text: TextView = itemView.findViewById(R.id.name_01)
        val img: ImageView = itemView.findViewById(R.id.head_01)
    }
}