package com.terry.login_kotlin.Fragments

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.terry.login_kotlin.*

@SuppressLint("Registered")
class Fragment_02 : Fragment() {
    private var contactList: RecyclerView? = null
    private var contactNames: Array<String>? = null
    private var layoutManager: LinearLayoutManager? = null
    private var letterView: LinkManList? = null
    private var adapter: ContactAdapter? = null

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_02, container, false)
    }

    @SuppressLint("MissingSuperCall")
    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        contactNames = arrayOf(
            "宋江", "卢俊义", "吴用", "公孙胜", "关胜", "林冲", "秦明", "呼延灼", "花荣", "柴进", "李应", "朱仝", "鲁智深",
            "武松", "董平", "张清", "杨志", "徐宁", "索超", "戴宗", "刘唐", "李逵", "史进", "穆弘",
            "雷横", "李俊", "阮小二", "张横", "阮小五", " 张顺", "阮小七", "杨雄", "石秀", "解珍",
            " 解宝", "燕青", "朱武", "黄信", "孙立", "宣赞", "郝思文", "韩滔", "彭玘", "单廷珪",
            "魏定国", "萧让", "裴宣", "欧鹏", "邓飞", " 燕顺", "杨林", "凌振", "蒋敬", "吕方",
            "郭 盛", "安道全", "皇甫端", "王英", "扈三娘", "鲍旭", "樊瑞", "孔明", "孔亮", "项充",
            "李衮", "金大坚", "马麟", "童威", "童猛", "孟康", "侯健", "陈达", "杨春", "郑天寿",
            "陶宗旺", "宋清", "乐和", "龚旺", "丁得孙", "穆春", "曹正", "宋万", "杜迁", "薛永", "施恩",
            "周通", "李忠", "杜兴", "汤隆", "邹渊", "邹润", "朱富", "朱贵", "蔡福", "蔡庆", "李立",
            "李云", "焦挺", "石勇", "孙新", "顾大嫂", "张青", "孙二娘", " 王定六", "郁保四", "白胜",
            "时迁", "段景柱"
        )
        contactList = activity!!.findViewById<RecyclerView>(R.id.contact_list)
        letterView = activity!!.findViewById<LinkManList>(R.id.letter_view)
        layoutManager = LinearLayoutManager(this.activity)
        adapter = ContactAdapter(this, contactNames!!)

        contactList!!.layoutManager = layoutManager
        contactList!!.addItemDecoration(DividerItemDecoration(context, DividerItemDecoration.VERTICAL_LIST))
        contactList!!.adapter = adapter

        letterView!!.setCharacterListener(object : LinkManList.CharacterClickListener {
            override fun clickCharacter(character: String) {
                layoutManager!!.scrollToPositionWithOffset(adapter!!.getScrollPosition(character), 0)
            }

            override fun clickArrow() {
                layoutManager!!.scrollToPositionWithOffset(0, 0)
            }
        })
    }
}
