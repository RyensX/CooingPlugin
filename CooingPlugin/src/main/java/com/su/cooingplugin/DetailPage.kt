package com.su.cooingplugin

import android.util.Log
import com.su.basevideopluginframework.framework.Const
import com.su.basevideopluginframework.framework.VideoDetailFramework
import com.su.basevideopluginframework.framework.data.VideoDetailInfo
import com.su.basevideopluginframework.framework.data.VideoPlayInfo
import com.su.basevideopluginframework.framework.data.VideoPlayListInfo
import org.jsoup.nodes.Document


/**
 *
 * Created by Ryens.
 * https://github.com/RyensX
 */
object DetailPage : VideoDetailFramework() {

    override fun videoDetail(document: Document): VideoDetailInfo? {
        var name = ""
        var desc = ""
        var cover = ""
        document.getElementsByClass("text-2xl xl:hidden mb-4").first()?.let { info ->
            name = info.ownText()

        }
        document.getElementsByClass("dark:bg-slate-900 rounded-2xl shadow-2xl w-full h-full bg-center bg-cover")
            .first()?.let {
                cover = it.attr("style").run { substring(indexOf("('") + 2, indexOf("')")) }
            }
        document.getElementById("vod_content")?.let {
            desc = it.text()
        }

        val playList = document.getElementById("playlist_1")?.children()?.map {
            it.text()
        } ?: emptyList()

        Log.d("videoDetail","name=$name desc=$desc cover=$cover")

        return VideoDetailInfo(name, desc, 0F, Const.url(cover), "",
            document.getElementsByAttributeValueContaining("class", "video_list play_list_asc")
                .mapIndexed { index, video ->
                    //每个正序播放列表
                    VideoPlayListInfo(
                        playList.getOrElse(index) { "播放列表${index + 1}" },
                        video.children().map {
                            VideoPlayInfo(
                                it.text(),
                                it.attr("onclick")
                                    .run { substring(indexOf("'/") + 1, indexOf("/'")) })
                        })
                })
    }

}