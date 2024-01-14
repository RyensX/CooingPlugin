package com.su.cooingplugin

import android.app.Activity
import android.os.Bundle
import android.widget.TextView

class MainActivity : Activity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(TextView(this).apply {
            text = "这是一个媒体盒子视频插件框架工程\n请通过媒体盒子（https://ryensx.github.io/MediaBoxPluginRepository/）安装并打开"
        })
    }
}