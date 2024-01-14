package com.su.cooingplugin

import com.su.basevideopluginframework.BasePluginFactory
import com.su.mediabox.pluginapi.components.*

/**
 * 每个插件必须实现本类
 *
 * 注意包和类名都要相同，且必须提供公开的无参数构造方法
 */
class MainPluginFactory : BasePluginFactory() {

    override val host: String = "https://cooing.cc"

    /**
     * 自动扫描已实现组件进行注册
     */
    override fun <T : IBasePageDataComponent> createComponent(clazz: Class<T>): T? =
        when (clazz) {
            IHomePageDataComponent::class.java -> HomePage as T
            IMediaDetailPageDataComponent::class.java -> DetailPage as T
            IVideoPlayPageDataComponent::class.java -> PlayPage as T
            IMediaSearchPageDataComponent::class.java -> SearchPage as T
            else -> null
        }

}