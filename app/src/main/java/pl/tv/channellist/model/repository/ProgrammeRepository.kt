package pl.tv.channellist.model.repository

import pl.tv.channellist.model.data.TvProgramme
import pl.tv.channellist.model.data.TvWebScrapper
import javax.inject.Inject
import javax.inject.Named

class ProgrammeRepository @Inject constructor(@Named("prefixUrl")val prefix: String, @Named("channelList") val channelList: List<String>,
                                              @Named("logoList") val logoList:List<String>,
                                              private val tvWebScrapper: TvWebScrapper) {



    fun doFindProgrammes():List<TvProgramme>{

        val programmes = mutableListOf<TvProgramme>()

        for(i in channelList.indices){
            programmes.add(TvProgramme(channelList[i],logoList[i],tvWebScrapper.scrapFromUrl(prefix,channelList[i])))
        }


        return programmes
    }







}