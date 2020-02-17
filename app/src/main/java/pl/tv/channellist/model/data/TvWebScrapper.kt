package pl.tv.channellist.model.data

import org.jsoup.Jsoup
import org.jsoup.nodes.Document
import javax.inject.Inject
import javax.inject.Named

class TvWebScrapper @Inject constructor(
    @Named("firstQuery") val firstQuery: String,
    @Named("lastTimeAndCategorySelection") val timeAndCategoryLastQuery: String,
    @Named("nextTitleSelection") val titleNextQuery: String,
    @Named("lastTitleSelection") val titleLastQuery: String
) {


    fun scrapFromUrl(prefix: String, channel: String): List<PendingProgramme> {
        return scrapData(prefix + channel)
    }

    private fun scrapData(url: String): List<PendingProgramme> {

        val document = Jsoup.connect(url).get() as Document

        val programmeList: MutableList<PendingProgramme> = mutableListOf()

        val programmeTitles =
            document.body().selectFirst(firstQuery).select(titleNextQuery)
                .select(titleLastQuery)
        val categoriesAndTimes =  document.body().selectFirst(firstQuery).select(timeAndCategoryLastQuery)


        for (i in 0 until categoriesAndTimes.size step 2) {
            programmeList.add(
                PendingProgramme(
                    categoriesAndTimes[i].text(),
                    "",
                    categoriesAndTimes[i + 1].text()
                )
            )
        }

        for (i in 0 until programmeTitles.size) {
            programmeList[i].programmeName = programmeTitles[i].text()
        }

        return programmeList
    }


}