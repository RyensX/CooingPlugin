package com.su.cooingplugin

import com.su.basevideopluginframework.framework.VideoPlayFramework
import org.jsoup.nodes.Document


/**
 *
 * Created by Ryens.
 * https://github.com/RyensX
 */
object PlayPage : VideoPlayFramework() {
    override suspend fun getVideoEpisodeName(doc: Document): String {
        return doc.getElementsByClass("cursor-auto dark:text-white dark:bg-slate-800 sm:px-12 py-2 rounded-xl")
            .first()?.ownText()?.substring(5) ?: ""
    }
}