package com.knight.knightkt.ui.main

import android.content.Context
import android.content.Intent
import android.os.Bundle
import com.knight.knightkt.R
import com.lodz.android.corekt.anko.bindView
import com.lodz.android.pandora.base.activity.AbsActivity
import com.lodz.android.pandora.widget.navigation.BaseNavigationView

class MainActivity : AbsActivity() {

    private val mNavigationMenu by bindView<BaseNavigationView>(R.id.navigation_view)

    companion object {
        @JvmStatic
        fun start(context: Context) {
            val intent = Intent(context, MainActivity::class.java)
            context.startActivity(intent)
        }
    }

    override fun getAbsLayoutId(): Int = R.layout.activity_main


    override fun findViews(savedInstanceState: Bundle?) {
        super.findViews(savedInstanceState)
    }

    override fun setListeners() {
        super.setListeners()
    }

}