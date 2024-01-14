package com.su.cooingplugin

import android.util.Log
import com.su.basevideopluginframework.framework.HomeFramework
import com.su.basevideopluginframework.framework.data.VideoClassifyInfo
import com.su.basevideopluginframework.framework.data.VideoInfo
import org.jsoup.nodes.Document


/**
 *
 * Created by Ryens.
 * https://github.com/RyensX
 */
object HomePage : HomeFramework() {

    override fun classifyList(doc: Document): List<VideoClassifyInfo>? {
        val url = doc.baseUri()
        Log.d("获取首页分类", url)
        val infos = mutableListOf<VideoClassifyInfo>()
        doc.getElementsByClass("grid grid-cols-3 gap-3 md:grid-cols-4 xl:grid-cols-6 lg:grid-cols-5 md:gap-4 place-content-center")
            .forEach { element ->
                //推荐类型
                val name = element.previousElementSibling()?.text() ?: ""
                Log.d("获取首页分类", "类型=$name")
                VideoClassifyInfo(
                    name,
                    "",
                    element.getElementsByClass("text-center text-sm relative drak:bg-black")
                        .map { element ->
                            var vName = ""
                            var vCover = ""
                            var url = ""
                            element.getElementsByTag("img").first()?.let { img ->
                                vCover = img.attr("data-original")
                                img.parent()?.let {
                                    url = it.attr("onClick")
                                        .run { substring(indexOf("'/") + 1, indexOf("/'")) }
                                }
                            }
                            element.getElementsByTag("span").first()?.let { info ->
                                vName = info.text()
                            }
                            Log.d("获取首页分类", "作品: name=$vName cover=$vCover url=$url")
                            VideoInfo(vName, vCover, url, "")
                        }).also {
                    infos.add(it)
                }
            }
        return infos
    }
}