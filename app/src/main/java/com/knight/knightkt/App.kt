package com.knight.knightkt

import androidx.multidex.MultiDex
import com.lodz.android.corekt.network.NetworkManager
import com.lodz.android.corekt.threadpool.ThreadPoolManager
import com.lodz.android.pandora.base.application.BaseApplication

/**
 * @ProjectName : KnightKt
 * @Author : Yangjw
 * @Time : 2021/3/29
 * @Description :
 */
class App : BaseApplication() {

    companion object {
        @JvmStatic
        fun get(): App = BaseApplication.get() as App
    }

    override fun onExit() {
        ThreadPoolManager.get().releaseAll()
        NetworkManager.get().release(this)
        NetworkManager.get().clearNetworkListener()
    }

    override fun onStartCreate() {
        MultiDex.install(this)
    }


}