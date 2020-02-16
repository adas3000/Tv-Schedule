package pl.tv.channellist.model.data

import org.jsoup.Jsoup

class TvWebScrapper() {


    fun scrapFromUrl(prefix: String, channel: String):List<PendingProgramme>{
        return scrapData(prefix,channel)
    }


    private fun scrapData(prefix: String, channel: String):List<PendingProgramme>{

        val body = Jsoup.connect(prefix+channel).get().body()




    }


}