package com.terry.login_kotlin


import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.terry.login_kotlin.Fragments.Fragment_01
import com.terry.login_kotlin.Fragments.Fragment_02
import com.terry.login_kotlin.Fragments.Fragment_03

class MainActivity : AppCompatActivity() {
    private var bottomNavigationView: BottomNavigationView? = null
    private var fragment1: Fragment_01? = null
    private var fragment2: Fragment_02? = null
    private var fragment3: Fragment_03? = null
    private var fragments: Array<Fragment>? = null
    private var lastFragment: Int = 0//用于记录上个选择的Fragment
    //判断选择的菜单
    private val changeFragment =
        BottomNavigationView.OnNavigationItemSelectedListener { item ->
            when (item.itemId) {
                R.id.menu_message -> {
                    if (lastFragment != 0) {
                        switchFragment(lastFragment, 0)
                        lastFragment = 0

                    }

                    return@OnNavigationItemSelectedListener true
                }
                R.id.menu_linkman -> {
                    if (lastFragment != 1) {
                        switchFragment(lastFragment, 1)
                        lastFragment = 1

                    }

                    return@OnNavigationItemSelectedListener true
                }
                R.id.menu_personalhome -> {
                    if (lastFragment != 2) {
                        switchFragment(lastFragment, 2)
                        lastFragment = 2

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
        lastFragment = 0
        supportFragmentManager.beginTransaction().replace(R.id.content, fragment1!!).show(fragment1!!).commit()
        bottomNavigationView = findViewById(R.id.nav_view)

        bottomNavigationView!!.setOnNavigationItemSelectedListener(changeFragment)
    }

    //切换Fragment
    private fun switchFragment(lastFragment: Int, index: Int) {
        val transaction = supportFragmentManager.beginTransaction()
        transaction.hide(fragments!![lastFragment])//隐藏上个Fragment
        if (!fragments!![index].isAdded) {
            transaction.add(R.id.content, fragments!![index])


        }
        transaction.show(fragments!![index]).commitAllowingStateLoss()
    }

    override fun onNewIntent(intent: Intent?) {
        super.onNewIntent(intent)
        var id:Int = intent!!.getIntExtra("Fragment",0)
        if(id == 2){
            switchFragment(0,2)
        }else if(id == 0){
            switchFragment(0,0)
        }
    }

}