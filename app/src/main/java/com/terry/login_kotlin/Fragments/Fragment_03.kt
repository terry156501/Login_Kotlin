package com.terry.login_kotlin.Fragments

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.terry.login_kotlin.*
import com.terry.login_kotlin.RichItemViewBinder
import me.drakeet.multitype.MultiTypeAdapter
import java.util.ArrayList


class Fragment_03 : Fragment() {
    private lateinit var multiTypeAdapter: MultiTypeAdapter
    private lateinit var items: MutableList<Any>
    private val spacing:Int = 10

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_03, container, false)
    }

    @SuppressLint("MissingSuperCall")
    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val recyclerView = activity!!.findViewById<RecyclerView>(R.id.RecyclerView)


        val gridLayoutManager = GridLayoutManager(this.activity, 6)

        gridLayoutManager.spanSizeLookup = object : GridLayoutManager.SpanSizeLookup() {
            override fun getSpanSize(i: Int): Int {
                return when {
                    items[i] is RichItem_hp -> 6/1
                    items[i] is RichItem_igon -> 6/3
                    items[i] is RichItem -> 6/1
                    else -> 6
                }
            }
        }
        recyclerView?.layoutManager = gridLayoutManager
        recyclerView.addItemDecoration(SpaceDecoration(gridLayoutManager.spanCount,spacing,false))
        items = ArrayList()
        multiTypeAdapter = MultiTypeAdapter(items)
        multiTypeAdapter.register(ImageItemViewBinder())
        multiTypeAdapter.register(TextItemViewBinder())
        multiTypeAdapter.register(RichItemViewBinder())
        multiTypeAdapter.register(RichItemViewBinder_igon())
        multiTypeAdapter.register(RichItemViewBinder_hp())

        recyclerView?.adapter = multiTypeAdapter
        requestData()

    }

    private fun requestData() {
        items.clear()
        items.add(RichItem_hp("Terry",R.mipmap.ic_launcher))
        items.add(RichItem_igon("Test_01",R.mipmap.ic_launcher))
        items.add(RichItem_igon("Test_02",R.mipmap.ic_launcher))
        items.add(RichItem_igon("Test_03",R.mipmap.ic_launcher))
        for(i in 0..9)
        {
            items.add(RichItem("Try$i",R.mipmap.ic_launcher,view!!))
        }
        multiTypeAdapter.notifyDataSetChanged()
    }
}