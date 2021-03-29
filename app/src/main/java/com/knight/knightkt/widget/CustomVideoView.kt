package com.knight.knightkt.widget

import android.content.Context
import android.util.AttributeSet
import android.widget.VideoView

/**
 * @ProjectName : KnightKt
 * @Author : Yangjw
 * @Time : 2021/3/29
 * @Description :自定义登录界面背景动画控件
 *
 * 需要处理的技术点分析:
 * 如何读取资源文件视频
 * 如何测量
 * 如何根据视频大小计算应该缩放的比例大小 解决任意尺寸视频手机不留黑边
 * 如何让图片的封面缩放大小和视频的缩放大小吻合
 * 如何解决调用onStart短暂黑屏问题
 */
class CustomVideoView : VideoView {

    var videoWidth: Int = 0
    var videoheight: Int = 0
    var displayAspectRatio: Int = 0

    constructor(context: Context?) : super(context) {
        init(context)
    }

    constructor(context: Context?, attrs: AttributeSet?) : super(context, attrs) {
        init(context)
    }

    constructor(context: Context?, attrs: AttributeSet?, defStyleAttr: Int) : super(
        context,
        attrs,
        defStyleAttr
    ) {
        init(context)
    }

    constructor(
        context: Context?,
        attrs: AttributeSet?,
        defStyleAttr: Int,
        defStyleRes: Int
    ) : super(context, attrs, defStyleAttr, defStyleRes) {
        init(context)
    }


    fun init(context: Context?) {
        videoWidth = context?.resources?.displayMetrics?.widthPixels ?: 100
        videoheight = context?.resources?.displayMetrics?.heightPixels ?: 100

    }
}