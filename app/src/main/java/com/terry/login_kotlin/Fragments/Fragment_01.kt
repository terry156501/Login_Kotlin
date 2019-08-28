package com.terry.login_kotlin.Fragments

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.terry.login_kotlin.*
import me.drakeet.multitype.MultiTypeAdapter
import java.util.ArrayList

class Fragment_01 : Fragment() {
    private lateinit var multiTypeAdapter: MultiTypeAdapter
    private lateinit var items: MutableList<Any>
    private val spacing:Int = 10

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_01, container, false)
    }

    @SuppressLint("MissingSuperCall")
    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val recyclerView = activity!!.findViewById<RecyclerView>(R.id.fragment_01_RecyclerView)


        val gridLayoutManager = GridLayoutManager(this.activity, 6)

        gridLayoutManager.spanSizeLookup = object : GridLayoutManager.SpanSizeLookup() {
            override fun getSpanSize(i: Int): Int {
                return when {
                    items[i] is ChatItem -> 6/1
                    else -> 6
                }
            }
        }
        recyclerView?.layoutManager = gridLayoutManager
        recyclerView.addItemDecoration(SpaceDecoration(gridLayoutManager.spanCount,spacing,false))
        items = ArrayList()
        multiTypeAdapter = MultiTypeAdapter(items)
        multiTypeAdapter.register(ChatItemViewBinder())

        recyclerView?.adapter = multiTypeAdapter
        requestData()

    }

    private fun requestData() {
        items.clear()
        items.add(ChatItem("Terry",R.mipmap.ic_launcher))
        items.add(ChatItem("Lee",R.mipmap.ic_launcher))
        multiTypeAdapter.notifyDataSetChanged()
    }
}