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
import java.util.regex.Pattern


@SuppressLint("Registered")
class RegisterActivity : AppCompatActivity() {

    private var mRegisterButton: Button? = null//注册按钮
    private var mBack:Button? = null
    private var mUser: EditText? = null
    private var mPhone: EditText? = null
    private var mKey: EditText? = null
    private var mKey02:EditText? = null
    private var userName:String? = null
    private var psw:String? = null
    private var pswAgain:String? = null
    private var phoneNum:String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)
        init()
    }
    private fun init() {

        mUser = findViewById(R.id.input_identity_text)
        mKey = findViewById(R.id.input_password_text)
        mKey02 = findViewById(R.id.input_password02_text)
        mPhone = findViewById(R.id.input_phone_text)
        mRegisterButton = findViewById(R.id.register_button)
        mBack = findViewById(R.id.back_button)

        var regPsw = Regex("[A-Z,a-z,0-9,?,!,(,)]{6,16}")
        var regPsw_string = Regex("[A-Z]")
        var regPsw_symbol = Regex("[?,!,(,)]")
        fun checkPhoneNum(num: String): Boolean{
            val regExp = "^((13[0-9])|(14[5,7,9])|(15[^4])|(18[0-9])|(17[0,1,3,5,6,7,8]))\\d{8}\$"
            val p = Pattern.compile(regExp)
            val m = p.matcher(num)
            return m.matches()
        }

        mBack!!.setOnClickListener {
            val intent = Intent(this@RegisterActivity, LoginTestActivity::class.java)
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
                TextUtils.isEmpty(phoneNum.toString()) -> {
                    Toast.makeText(this@RegisterActivity, "请输入手机号", Toast.LENGTH_SHORT).show()
                    return@OnClickListener
                }
                !checkPhoneNum(phoneNum.toString()) -> {
                    Toast.makeText(this@RegisterActivity, "请输入正确的手机号", Toast.LENGTH_SHORT).show()
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
                !regPsw.matches(psw.toString()) -> {
                    Toast.makeText(this@RegisterActivity, "密码格式错误", Toast.LENGTH_SHORT).show()
                    return@OnClickListener
                }
                !regPsw_string.containsMatchIn(psw.toString()) -> {
                    Toast.makeText(this@RegisterActivity, "密码必须包含大写字母", Toast.LENGTH_SHORT).show()
                    return@OnClickListener
                }
                !regPsw_symbol.containsMatchIn(psw.toString()) -> {
                    Toast.makeText(this@RegisterActivity, "密码必须包含?,!,(,)字符", Toast.LENGTH_SHORT).show()
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
                    this@RegisterActivity.finish()
                }
            }
        })
    }
    private fun getEditString() {
        userName = mUser!!.text.toString().trim { it <= ' ' }
        psw = mKey!!.text.toString().trim { it <= ' ' }
        pswAgain = mKey02!!.text.toString().trim { it <= ' ' }
        phoneNum = mPhone!!.text.toString().trim{it <= ' '}

    }

    private fun isExistUserName(userName: String?): Boolean {
        var has_userName = false
        // "loginInfo"
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
        //把密码用MD5加密
        val md5Psw = MD5Utils.md5(psw!!)
        //loginInfo表示文件名
        val sp = getSharedPreferences("loginInfo", MODE_PRIVATE)
        val editor = sp.edit()
        //以用户名为key，密码为value保存在SharedPreferences中
        //key,value
        editor.putString(userName, md5Psw)
        editor.apply()
    }
}