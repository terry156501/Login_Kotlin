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


@SuppressLint("Registered")
class RegisterActivity : AppCompatActivity() {

    private var mRegisterButton: Button? = null//注册按钮
    private var mBack:Button? = null
    private var mUser: EditText? = null
    private var mKey: EditText? = null
    private var mKey02:EditText? = null
    private var userName:String? = null
    private var psw:String? = null
    private var pswAgain:String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)
        init()
        mUser = findViewById<View>(R.id.input_identity_text) as EditText
        mKey = findViewById<View>(R.id.input_password_text) as EditText
        mKey02 = findViewById<View>(R.id.input_password02_text) as EditText
        mRegisterButton = findViewById<View>(R.id.register_button) as Button
        mBack = findViewById<View>(R.id.back_button) as Button
    }
    private fun init() {

        mUser = findViewById<View>(R.id.input_identity_text) as EditText
        mKey = findViewById<View>(R.id.input_password_text) as EditText
        mKey02 = findViewById<View>(R.id.input_password02_text) as EditText
        mRegisterButton = findViewById<View>(R.id.register_button) as Button
        mBack = findViewById<View>(R.id.back_button) as Button

        mBack!!.setOnClickListener {
            val intent = Intent(this@RegisterActivity,LoginActivity::class.java)
            startActivity(intent)
        }
        //注册按钮
        mRegisterButton!!.setOnClickListener(View.OnClickListener {
            getEditString()
            when {
                TextUtils.isEmpty(userName) -> {
                    Toast.makeText(this@RegisterActivity, "请输入用户名", Toast.LENGTH_SHORT).show()
                    return@OnClickListener
                }
                TextUtils.isEmpty(psw) -> {
                    Toast.makeText(this@RegisterActivity, "请输入密码", Toast.LENGTH_SHORT).show()
                    return@OnClickListener
                }
                TextUtils.isEmpty(pswAgain) -> {
                    Toast.makeText(this@RegisterActivity, "请再次输入密码", Toast.LENGTH_SHORT).show()
                    return@OnClickListener
                }
                psw != pswAgain -> {
                    Toast.makeText(this@RegisterActivity, "输入两次的密码不一样", Toast.LENGTH_SHORT).show()
                    return@OnClickListener
                }
                isExistUserName(userName) -> {
                    Toast.makeText(this@RegisterActivity, "此账户名已经存在", Toast.LENGTH_SHORT).show()
                    return@OnClickListener
                }
                else -> {
                    Toast.makeText(this@RegisterActivity, "注册成功", Toast.LENGTH_SHORT).show()
                    saveRegisterInfo(userName, psw)
                    val data = Intent()
                    data.putExtra("userName", userName)
                    setResult(Activity.RESULT_OK, data)
                    //RESULT_OK为Activity系统常量，状态码为-1，
                    // 表示此页面下的内容操作成功将data返回到上一页面，如果是用back返回过去的则不存在用setResult传递data值
                    this@RegisterActivity.finish()
                }
            }
        })
    }
    private fun getEditString() {
        userName = mUser!!.text.toString().trim { it <= ' ' }
        psw = mKey!!.text.toString().trim { it <= ' ' }
        pswAgain = mKey02!!.text.toString().trim { it <= ' ' }
    }

    private fun isExistUserName(userName: String?): Boolean {
        var has_userName = false
        //mode_private SharedPreferences sp = getSharedPreferences( );
        // "loginInfo", MODE_PRIVATE
        val sp = getSharedPreferences("loginInfo", MODE_PRIVATE)
        //获取密码
        val spPsw = sp.getString(userName, "")//传入用户名获取密码
        //如果密码不为空则确实保存过这个用户名
        if (!TextUtils.isEmpty(spPsw)) {
            has_userName = true
        }
        return has_userName
    }

    private fun saveRegisterInfo(userName: String?, psw: String?) {
        val md5Psw = MD5Utils.md5(psw!!)//把密码用MD5加密
        //loginInfo表示文件名, mode_private SharedPreferences sp = getSharedPreferences( );
        val sp = getSharedPreferences("loginInfo", MODE_PRIVATE)
        //获取编辑器， SharedPreferences.Editor  editor -> sp.edit();
        val editor = sp.edit()
        //以用户名为key，密码为value保存在SharedPreferences中
        //key,value,如键值对，editor.putString(用户名，密码）;
        editor.putString(userName, md5Psw)
        //提交修改 editor.commit();
        editor.apply()
    }
}