package com.terry.login_kotlin

import android.annotation.SuppressLint
import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.text.TextUtils
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.terry.login_kotlin.view.LoginTestActivity

@SuppressLint("Changed")
class ChangeKeyActivity: AppCompatActivity() {

    private var mUser:EditText? = null
    private var mOld:EditText? = null
    private var mNew:EditText? = null
    private var mAffirm:Button? = null
    private var mBack:Button? = null
    private var userName:String? = null
    private var oldKey:String? = null
    private var newKey:String? = null
    private var oldTKey:String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_changekey)
        init()
    }
    private fun init() {
        mUser = findViewById(R.id.input_change_id)
        mOld = findViewById(R.id.input_change_oldkey)
        mNew = findViewById(R.id.input_change_newkey)
        mAffirm = findViewById(R.id.change_affirm_button)
        mBack = findViewById(R.id.change_back_button)

        val regPsw = Regex("[A-Z,a-z,0-9,?,!,(,)]{6,16}")

        mBack!!.setOnClickListener {
            val intent = Intent(this@ChangeKeyActivity, LoginTestActivity::class.java)
            startActivity(intent)
        }

        mAffirm!!.setOnClickListener (View.OnClickListener{
            getEditString()

            val md5Psw = MD5Utils.md5(oldKey!!)
            oldTKey = readPsw(userName)
            if (TextUtils.isEmpty(userName)) {
                Toast.makeText(this@ChangeKeyActivity, "请输入用户名", Toast.LENGTH_SHORT).show()
                return@OnClickListener
            } else if (TextUtils.isEmpty(oldKey)) {
                Toast.makeText(this@ChangeKeyActivity, "请输入密码", Toast.LENGTH_SHORT).show()
                return@OnClickListener
            } else if (oldTKey != null && !TextUtils.isEmpty(oldTKey) && md5Psw != oldTKey) {
                Toast.makeText(this@ChangeKeyActivity, "输入的用户名和密码不一致", Toast.LENGTH_SHORT).show()
                return@OnClickListener
            }else if(!regPsw.matches(newKey.toString())) {
                Toast.makeText(this@ChangeKeyActivity, "密码格式错误", Toast.LENGTH_SHORT).show()
                return@OnClickListener
            }else if(md5Psw == oldTKey) {
                Toast.makeText(this@ChangeKeyActivity, "修改成功", Toast.LENGTH_SHORT).show()
                saveChangeInfo(userName, newKey)
                val data = Intent()
                data.putExtra("userName", userName)
                setResult(Activity.RESULT_OK, data)
                this@ChangeKeyActivity.finish()
                val intent = Intent(this@ChangeKeyActivity, LoginActivity::class.java)
                startActivity(intent)
            }else{
                Toast.makeText(this@ChangeKeyActivity, "此用户名不存在", Toast.LENGTH_SHORT).show()
            }
        })
    }
    private fun getEditString() {
        userName = mUser!!.text.toString().trim { it <= ' ' }
        oldKey = mOld!!.text.toString().trim { it <= ' ' }
        newKey = mNew!!.text.toString().trim { it <= ' ' }
    }
    private fun readPsw(userName: String?): String? {
        val sp = getSharedPreferences("loginInfo", MODE_PRIVATE)
        return sp.getString(userName, "")
    }
    private fun saveChangeInfo(userName: String?, key: String?) {
        val md5Psw = MD5Utils.md5(key!!)
        val sp = getSharedPreferences("loginInfo", MODE_PRIVATE)
        val editor = sp.edit()
        //改变密码
        editor.putString(userName, md5Psw)
        editor.apply()
    }
}