package com.terry.login_kotlin

import android.content.Intent
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import me.drakeet.multitype.ItemViewBinder

class TextItemViewBinder: ItemViewBinder<TextItem, TextItemViewBinder.ViewHolder>() {

    override fun onCreateViewHolder(inflater: LayoutInflater, parent: ViewGroup): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.text_item,parent ,false)
        val holder = ViewHolder(view)
        holder.fooView.setOnClickListener{
            Toast.makeText(it.context,"TextView",Toast.LENGTH_SHORT).show()
            val intent = Intent(it.context, Text_01Activity::class.java)
            it.context.startActivity(intent)
        }
        return holder
    }

    override fun onBindViewHolder(holder: ViewHolder, item: TextItem) {
        holder.fooView.text = item.value
        Log.d("ItemViewBinder API", "position: ${getPosition(holder)}")
        Log.d("ItemViewBinder API", "items: $adapterItems")
        Log.d("ItemViewBinder API", "adapter: $adapter")
        Log.d("More", "Context: ${holder.itemView.context}")
    }

    class ViewHolder(itemView : View): RecyclerView.ViewHolder(itemView) {
        val fooView: TextView = itemView.findViewById(R.id.text)
    }
}