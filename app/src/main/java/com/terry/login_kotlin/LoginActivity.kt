package com.terry.login_kotlin

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.text.TextUtils
import android.view.View
import android.widget.*
import androidx.appcompat.app.AppCompatActivity


class LoginActivity : AppCompatActivity() {

    private var mRegisterButton: Button? = null//注册按钮
    private var mLoginButton: Button? = null//登录按钮
    private var mChange: TextView? = null
    private var mUser: EditText? = null
    private var mKey: EditText? = null
    private var userName:String? = null
    private var psw:String? = null
    private var spPsw:String? = null
    private var mCheck:CheckBox? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        init()
    }
    private fun init() {
        mRegisterButton = findViewById(R.id.register_button)
        mLoginButton = findViewById(R.id.login_button)
        mChange = findViewById(R.id.change_button)
        mUser = findViewById(R.id.input_user_text)
        mKey = findViewById(R.id.input_key_text)
        mCheck = findViewById(R.id.rem_psw)

        val sp = getSharedPreferences("loginInfo", MODE_PRIVATE)
        val editor = sp.edit()
        var checkstatus = mCheck
        if(checkstatus!!.isChecked)
        {
            editor.putString("username",mUser!!.text.toString().trim())
            editor.putString("password",mKey!!.text.toString().trim())
        }

        mRegisterButton!!.setOnClickListener{
            val intent = Intent(this@LoginActivity,RegisterActivity::class.java)
            startActivity(intent)
        }

        mChange!!.setOnClickListener{
            val intent = Intent(this@LoginActivity,ChangeKeyActivity::class.java)
            startActivity(intent)
        }

        mLoginButton!!.setOnClickListener(View.OnClickListener {
            getEditString()
            val md5Psw = MD5Utils.md5(psw!!)
            spPsw = readPsw(userName)
            var regPsw = Regex("[A-Z,a-z,0-9,?,!,(,)]{6,16}")
            // 用户名为空
            if (TextUtils.isEmpty(userName)) {
                Toast.makeText(this@LoginActivity, "请输入用户名", Toast.LENGTH_SHORT).show()
                return@OnClickListener
                //密码为空
            } else if (TextUtils.isEmpty(psw)) {
                Toast.makeText(this@LoginActivity, "请输入密码", Toast.LENGTH_SHORT).show()
                return@OnClickListener
                // 判断，输入的密码加密后，是否与保存在SharedPreferences中一致
            } else if (md5Psw == spPsw) {
                //一致登录成功
                Toast.makeText(this@LoginActivity, "登录成功", Toast.LENGTH_SHORT).show()
                //保存登录状态，在界面保存登录的用户名 saveLoginStatus 状态 , 用户名;
                saveLoginStatus(true, userName)
                //登录成功后关闭此页面进入主页
                val data = Intent()
                data.putExtra("isLogin", true)
                setResult(Activity.RESULT_OK, data)
                //销毁登录界面
                this@LoginActivity.finish()
                //跳转，登录成功的状态传递到 SuccessActivity 中
                startActivity(Intent(this@LoginActivity, SuccessActivity::class.java))
                return@OnClickListener
                //用户名密码不匹配
            } else if (spPsw != null && !TextUtils.isEmpty(spPsw) && md5Psw != spPsw) {
                Toast.makeText(this@LoginActivity, "输入的用户名和密码不一致", Toast.LENGTH_SHORT).show()
                return@OnClickListener
            }
            else if(!regPsw.matches(mKey.toString()))
            {
                Toast.makeText(this@LoginActivity, "密码格式错误", Toast.LENGTH_SHORT).show()
            }
            //用户名不存在
            else {
                Toast.makeText(this@LoginActivity, "此用户名不存在", Toast.LENGTH_SHORT).show()
            }
        })
    }
    private fun getEditString() {
        userName = mUser!!.text.toString().trim { it <= ' ' }
        psw = mKey!!.text.toString().trim { it <= ' ' }
    }
    //读取输入的用户名，从SharePreference中找到与之匹配的密码并返回
    private fun readPsw(userName: String?): String? {
        val sp = getSharedPreferences("loginInfo", MODE_PRIVATE)
        return sp.getString(userName, "")
    }
    //保存登陆状态
    private fun saveLoginStatus(status: Boolean, userName: String?) {
        val sp = getSharedPreferences("loginInfo", MODE_PRIVATE)
        val editor = sp.edit()
        editor.putBoolean("isLogin", status)
        //存入登录状态时的用户名
        editor.putString("loginUserName", userName)
        editor.apply()
    }

//    显示数据， onActivityResult
//    startActivityForResult(intent, 1); 从注册界面中获取数据
//    LoginActivity -> startActivityForResult -> onActivityResult();
    override
    fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (data != null) {
            //是获取注册界面回传过来的用户名
            // getExtra().getString("***");
            val userName = data.getStringExtra("userName")
            if (!TextUtils.isEmpty(userName)) {
                //设置用户名到 input_user_text 控件
                mUser!!.setText(userName)
                //input_user_text控件的setSelection()方法来设置光标位置
                mUser!!.setSelection(userName!!.length)
            }
        }
    }

}


