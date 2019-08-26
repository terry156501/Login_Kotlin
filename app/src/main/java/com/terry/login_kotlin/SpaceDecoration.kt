package com.terry.login_kotlin

import android.graphics.Rect
import android.view.View
import androidx.recyclerview.widget.RecyclerView

class SpaceDecoration(private var spanCount:Int,private var spacing:Int,private var includeEdge:Boolean): RecyclerView.ItemDecoration() {


    override fun getItemOffsets(outRect: Rect, view: View, parent:RecyclerView, state:RecyclerView.State){
        val position:Int = parent.getChildAdapterPosition(view)
        val colum:Int = position % spanCount
        if(includeEdge){
            outRect.left = spacing - colum * spacing / spanCount
            outRect.right = (colum + 1) * spacing / spanCount

            if(position < spanCount){
                outRect.top = spacing
            }
            outRect.bottom = spacing
        }else{
            outRect.left = colum * spacing / spanCount
            outRect.right = spacing - (colum + 1) * spacing / spanCount
            if(position >= spanCount){
                outRect.top = spacing
            }
        }
    }

}