package com.terry.login_kotlin

import android.content.Intent
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.terry.login_kotlin.RichItemViewBinder_hp.ViewHolder
import me.drakeet.multitype.ItemViewBinder

class RichItemViewBinder_hp: ItemViewBinder<RichItem_hp, ViewHolder>() {

    override fun onCreateViewHolder(inflater: LayoutInflater, parent: ViewGroup): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.rich_hp_item, parent, false)
        val holder = ViewHolder(view)
        holder.text.setOnClickListener {
            Toast.makeText(it.context, "RichView_Text" , Toast.LENGTH_SHORT).show()
            val intent = Intent(it.context, Rich_hp_01Activity::class.java)
            it.context.startActivity(intent)
        }
        holder.img.setOnClickListener {
            Toast.makeText(it.context, "RichView_Img" , Toast.LENGTH_SHORT).show()
            val intent = Intent(it.context, Rich_hp_01Activity::class.java)
            it.context.startActivity(intent)
        }
        return holder
    }

    override fun onBindViewHolder(holder: ViewHolder, item: RichItem_hp) {
        holder.text.text = item.value
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
        val text: TextView = itemView.findViewById(R.id.rich_hp_text)
        val img: ImageView = itemView.findViewById(R.id.rich_hp_img)
    }

}