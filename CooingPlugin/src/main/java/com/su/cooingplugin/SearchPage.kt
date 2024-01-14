package com.su.cooingplugin

import com.su.basevideopluginframework.framework.Const
import com.su.basevideopluginframework.framework.VideoSearchFramework
import com.su.basevideopluginframework.framework.data.VideoSearchInfo
import com.su.basevideopluginframework.framework.data.VideoTag
import org.jsoup.nodes.Document


/**
 *
 * Created by Ryens.
 * https://github.com/RyensX
 */
object SearchPage : VideoSearchFramework() {

    override val searchUrlTemplate: String = "https://cooing.cc/vodsearch/%key----------%page---/"

    override suspend fun search(document: Document): List<VideoSearchInfo>? {
        val searchResult = mutableListOf<VideoSearchInfo>()
        document.getElementsByClass("text-2xl xl:hidden").forEach { element ->
            val name = element.ownText()
            element.nextElementSibling()?.let { info ->
                var img = info.getElementsByTag("img").first()?.attr("data-original")
                if (img != null) {
                    img = Const.url(img)
                }
                info.getElementsByTag("button").first()?.attr("onclick")
                    ?.run { substring(indexOf("'/") + 1, indexOf("/'")) }?.let { videoUrl ->
                        val otherInfoList =
                            info.getElementsByClass("line-clamp-1").map { it.ownText() }
                        searchResult.add(
                            VideoSearchInfo(
                                name,
                                img ?: "",
                                Const.url(videoUrl),
                                otherInfoList.getOrNull(0) ?: "",
                                otherInfoList.getOrNull(2) ?: "",
                                otherInfoList.getOrNull(4)?.substring(4)?.split(",")?.take(5)
                                    ?.map { VideoTag(it) }
                            )
                        )
                    }
            }
        }
        return searchResult
    }
}