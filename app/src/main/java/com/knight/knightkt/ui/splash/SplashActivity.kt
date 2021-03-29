package com.knight.knightkt.ui.splash

import android.Manifest
import android.content.DialogInterface
import android.os.Build
import android.os.Bundle
import com.knight.knightkt.App
import com.knight.knightkt.R
import com.knight.knightkt.ui.login.LoginActivity
import com.lodz.android.corekt.anko.*
import com.lodz.android.corekt.utils.StatusBarUtil
import com.lodz.android.pandora.base.activity.AbsActivity
import kotlinx.coroutines.GlobalScope
import permissions.dispatcher.*

/**
 * @ProjectName : KnightKt
 * @Author : Yangjw
 * @Time : 2021/3/29
 * @Description : 启动页
 */
@RuntimePermissions
class SplashActivity : AbsActivity() {


    override fun getAbsLayoutId(): Int = R.layout.activity_splash

    override fun findViews(savedInstanceState: Bundle?) {
        super.findViews(savedInstanceState)
        StatusBarUtil.setColor(window, getColorCompat(R.color.color_1E3C5C))
    }

    override fun endCreate() {
        super.endCreate()
        if (!isTopAndBottomActivityTheSame()) {
            finish()
            return
        }
        GlobalScope.runOnMainDelay(1000) {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                onRequestPermissionWithPermissionCheck()
            } else {
                jump2LoginActivity()
            }
        }
    }


    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        onRequestPermissionsResult(requestCode, grantResults)
    }

    /** 权限申请成功 */
    @NeedsPermission(
        Manifest.permission.READ_PHONE_STATE,// 手机状态
    )
    fun onRequestPermission() {
        if (!isPermissionGranted(Manifest.permission.READ_PHONE_STATE)) {
            return
        }
        jump2LoginActivity()
    }

    /** 用户拒绝后再次申请前告知用户为什么需要该权限 */
    @OnShowRationale(
        Manifest.permission.READ_PHONE_STATE,// 手机状态
    )
    fun onShowRationaleBeforeRequest(request: PermissionRequest) {
        request.proceed()//请求权限
    }

    /** 被拒绝 */
    @OnPermissionDenied(
        Manifest.permission.READ_PHONE_STATE,// 手机状态
    )
    fun onDenied() {
        onRequestPermissionWithPermissionCheck()//申请权限
    }

    /** 被拒绝并且勾选了不再提醒 */
    @OnNeverAskAgain(
        Manifest.permission.READ_PHONE_STATE,// 手机状态
    )
    fun onNeverAskAgain() {
        toastShort(R.string.splash_check_permission_tips)
        showPermissionCheckDialog()
        goAppDetailSetting()
    }

    /** 显示权限核对弹框 */
    private fun showPermissionCheckDialog() {
        val checkDialog = CheckDialog(getContext())
        checkDialog.setContentMsg(R.string.splash_check_permission_title)
        checkDialog.setPositiveText(
            R.string.splash_check_permission_confirm,
            DialogInterface.OnClickListener { dialog, which ->
                onRequestPermissionWithPermissionCheck()
                dialog.dismiss()
            })
        checkDialog.setNegativeText(
            R.string.splash_check_permission_unconfirmed,
            DialogInterface.OnClickListener { dialog, which ->
                goAppDetailSetting()
            })
        checkDialog.setCanceledOnTouchOutside(false)
        checkDialog.setOnCancelListener {
            toastShort(R.string.splash_check_permission_cancel)
            App.get().exit()
        }
        checkDialog.show()
    }


    fun jump2LoginActivity() {
        LoginActivity.start(getContext())
        finish()
    }

}