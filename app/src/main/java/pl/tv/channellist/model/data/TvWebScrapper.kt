package pl.tv.channellist.model.data

import org.jsoup.Jsoup
import javax.inject.Inject
import javax.inject.Named

class TvWebScrapper @Inject constructor(
    @Named("time")val displayTimeClass: String,
    @Named("title")val displayTitleClass: String,
    @Named("category")val displayCategoryClass: String
) {


    fun scrapFromUrl(prefix: String, channel: String):List<PendingProgramme>{
        return scrapData(prefix,channel)
    }
    
    private fun scrapData(prefix: String, channel: String):List<PendingProgramme>{

        val body = Jsoup.connect(prefix+channel).get()

        val programmeList:MutableList<PendingProgramme> = mutableListOf()


        for(i in body.select(displayTimeClass)){
            programmeList.add(PendingProgramme(i.text(),"",""))
        }

        return programmeList
    }


}