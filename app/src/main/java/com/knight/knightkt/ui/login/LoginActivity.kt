package com.knight.knightkt.ui.login

import android.content.Context
import android.content.Intent
import android.media.MediaPlayer
import android.net.Uri
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import com.knight.knightkt.R
import com.knight.knightkt.config.Constant
import com.knight.knightkt.ui.main.MainActivity
import com.knight.knightkt.utils.MeasureUtil
import com.knight.knightkt.widget.SystemVideoView
import com.lodz.android.corekt.anko.bindView
import com.lodz.android.pandora.base.activity.AbsActivity

/**
 * @ProjectName : KnightKt
 * @Author : Yangjw
 * @Time : 2021/3/29
 * @Description : 登录页
 */
class LoginActivity : AbsActivity() {

    /**
     * 背景视频
     */
    private val mVideoView by bindView<SystemVideoView>(R.id.video_view)

    /**
     * 游客登录按钮
     */
    private val mVisitorsLoginBtn by bindView<Button>(R.id.visitors_login_btn)

    /**
     * 登录按钮
     */
    private val mLoginBtn by bindView<Button>(R.id.login_btn)

    /**
     * 忘记密码
     */
    private val mForgetPasswordTv by bindView<TextView>(R.id.forget_password_tv)

    /**
     * 注册账号
     */
    private val mRegisterTv by bindView<TextView>(R.id.register_account_tv)

    /**
     * 账号输入框
     */
    private val mAccountEd by bindView<EditText>(R.id.account_ed)

    /**
     * 密码输入框
     */
    private val mPasswordEd by bindView<EditText>(R.id.password_ed)

    companion object {
        @JvmStatic
        fun start(context: Context) {
            val intent: Intent = Intent(context, LoginActivity::class.java)
            context.startActivity(intent)
        }
    }

    override fun getAbsLayoutId(): Int = R.layout.activity_login


    override fun findViews(savedInstanceState: Bundle?) {
        super.findViews(savedInstanceState)
        initVideoView()
    }

    override fun setListeners() {
        super.setListeners()
        //游客登录
        mVisitorsLoginBtn.setOnClickListener {
            MainActivity.start(getContext())
            finish()
        }
        //登录按钮
        mLoginBtn.setOnClickListener { }
        //忘记账号按钮
        mForgetPasswordTv.setOnClickListener { }
        //注册账号按钮
        mRegisterTv.setOnClickListener { }
    }

    private fun initVideoView() {
        mVideoView.displayAspectRatio = MeasureUtil.ASPECT_RATIO_PAVED_PARENT
        mVideoView.setCorver(R.drawable.bg_login)
        mVideoView.setVideoURI(Uri.parse(Constant.VIDEO_PATH))
        mVideoView.start()
        mVideoView.setOnCompletionListener(MediaPlayer.OnCompletionListener {
            mVideoView.seekTo(0)
            mVideoView.start()
        })
    }


    override fun onResume() {
        super.onResume()
        mVideoView.resume()
    }

    override fun onPause() {
        super.onPause()
        mVideoView.pause()
    }


    override fun onDestroy() {
        super.onDestroy()
        mVideoView.stopPlayback()
    }

}