package com.terry.login_kotlin


import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment

import com.google.android.material.bottomnavigation.BottomNavigationView

class MainActivity : AppCompatActivity() {
    private var bottomNavigationView: BottomNavigationView? = null
    private var fragment1: Fragment_01? = null
    private var fragment2: Fragment_02? = null
    private var fragment3: Fragment_03? = null
    private var fragments: Array<Fragment>? = null
    private var lastfragment: Int = 0//用于记录上个选择的Fragment
    //判断选择的菜单
    private val changeFragment =
        BottomNavigationView.OnNavigationItemSelectedListener { item ->
            when (item.itemId) {
                R.id.menu_message -> {
                    if (lastfragment != 0) {
                        switchFragment(lastfragment, 0)
                        lastfragment = 0

                    }

                    return@OnNavigationItemSelectedListener true
                }
                R.id.menu_linkman -> {
                    if (lastfragment != 1) {
                        switchFragment(lastfragment, 1)
                        lastfragment = 1

                    }

                    return@OnNavigationItemSelectedListener true
                }
                R.id.menu_personalhome -> {
                    if (lastfragment != 2) {
                        switchFragment(lastfragment, 2)
                        lastfragment = 2

                    }

                    return@OnNavigationItemSelectedListener true
                }
            }


            false
        }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        initFragment()
    }

    //初始化fragment和fragment数组
    private fun initFragment() {

        fragment1 = Fragment_01()
        fragment2 = Fragment_02()
        fragment3 = Fragment_03()
        fragments = arrayOf(fragment1!!, fragment2!!, fragment3!!)
        lastfragment = 0
        supportFragmentManager.beginTransaction().replace(R.id.content, fragment1!!).show(fragment1!!).commit()
        bottomNavigationView = findViewById(R.id.nav_view) as BottomNavigationView

        bottomNavigationView!!.setOnNavigationItemSelectedListener(changeFragment)
    }

    //切换Fragment
    private fun switchFragment(lastfragment: Int, index: Int) {
        val transaction = supportFragmentManager.beginTransaction()
        transaction.hide(fragments!![lastfragment])//隐藏上个Fragment
        if (fragments!![index].isAdded == false) {
            transaction.add(R.id.content, fragments!![index])


        }
        transaction.show(fragments!![index]).commitAllowingStateLoss()


    }
}