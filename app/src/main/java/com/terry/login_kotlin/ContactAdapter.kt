package com.terry.login_kotlin

import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import android.view.ViewGroup
import android.view.LayoutInflater
import android.view.View
import com.terry.login_kotlin.Fragments.Fragment_02
import java.util.*
import kotlin.collections.ArrayList
import kotlin.collections.HashMap


class ContactAdapter(mContext: Fragment_02, private var mContactNames:Array<String>) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    private var mLayoutInflater: LayoutInflater = LayoutInflater.from(mContext.activity)
    private var mContactList: MutableList<String>? = null // 联系人名称List（转换成拼音）
    private var resultList: MutableList<Contact>? = null // 最终结果（包含分组的字母）
    private var characterList: MutableList<String>? = null // 字母List

    enum class ITEM_TYPE {
        ITEM_TYPE_CHARACTER,
        ITEM_TYPE_CONTACT
    }

    init {

        handleContact()
    }

    private fun handleContact() {
        mContactList = ArrayList()
        val map = HashMap<String,String>()

        for (i in mContactNames.indices) {
            val pinyin = Utils.getPingYin(mContactNames[i])
            map[pinyin] = mContactNames[i]
            mContactList!!.add(pinyin)
        }
        mContactList!!.sort()

        resultList = ArrayList()
        characterList = ArrayList()

        for (i in mContactList!!.indices) {
            val name = mContactList!![i]
            val character = (name[0] + "").toUpperCase(Locale.ENGLISH)
            if (!characterList!!.contains(character)) {
                if (character.hashCode() >= "A".hashCode() && character.hashCode() <= "Z".hashCode()) { // 是字母
                    characterList!!.add(character)
                    resultList!!.add(Contact(character, ITEM_TYPE.ITEM_TYPE_CHARACTER.ordinal))
                } else {
                    if (!characterList!!.contains("#")) {
                        characterList!!.add("#")
                        resultList!!.add(Contact("#", ITEM_TYPE.ITEM_TYPE_CHARACTER.ordinal))
                    }
                }
            }

            resultList!!.add(Contact(map[name].toString(), ITEM_TYPE.ITEM_TYPE_CONTACT.ordinal))
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return if (viewType == ITEM_TYPE.ITEM_TYPE_CHARACTER.ordinal) {
            CharacterHolder(mLayoutInflater.inflate(R.layout.item_character, parent, false))
        } else {
            ContactHolder(mLayoutInflater.inflate(R.layout.item_contact, parent, false))
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        if (holder is CharacterHolder){
            holder.mTextView.text = resultList!![position].getmName()
        }
        else if (holder is ContactHolder) {
            holder.mTextView.text = resultList!![position].getmName()
        }
    }

    override fun getItemViewType(position: Int): Int {
        return resultList!![position].getmType()
    }

    override fun getItemCount(): Int {
        return if (resultList == null) 0 else resultList!!.size
    }

    class CharacterHolder(view: View) : RecyclerView.ViewHolder(view) {
        var mTextView:TextView = view.findViewById(R.id.character)
    }

    class ContactHolder(view: View) : RecyclerView.ViewHolder(view) {
        var mTextView:TextView = view.findViewById(R.id.contact_name)
    }

    fun getScrollPosition(character: String): Int {
        if (characterList!!.contains(character)) {
            for (i in resultList!!.indices) {
                if (resultList!![i].getmName() == character) {
                    return i
                }
            }
        }

        return -1 // -1不会滑动
    }
}