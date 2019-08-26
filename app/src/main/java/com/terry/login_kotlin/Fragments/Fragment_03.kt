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
                    items[i] is ImageItem -> 6/3
                    items[i] is TextItem -> 6/1
                    items[i] is RichItem -> 6/2
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

        recyclerView?.adapter = multiTypeAdapter
        requestData()

    }

    private fun requestData() {
        items.clear()
        for (i in 0..2) {
            items.add(ImageItem(R.mipmap.ic_launcher))
        }
        for (i in 0..4){
            items.add(TextItem("Hello!"))
        }
        for(i in 0..9){
            items.add(RichItem("Bye",R.mipmap.ic_launcher))
        }
        multiTypeAdapter.notifyDataSetChanged()
    }
}