package com.knight.knightkt.ui.login

import android.content.Context
import android.content.Intent
import com.knight.knightkt.R
import com.lodz.android.pandora.base.activity.AbsActivity

/**
 * @ProjectName : KnightKt
 * @Author : Yangjw
 * @Time : 2021/3/29
 * @Description : 登录页
 */
class LoginActivity : AbsActivity() {

    companion object {
        @JvmStatic
        fun start(context: Context) {
            val intent: Intent = Intent(context, LoginActivity::class.java)
            context.startActivity(intent)
        }
    }

    override fun getAbsLayoutId(): Int = R.layout.activity_login
}