package pl.tv.channellist.model.repository

import pl.tv.channellist.model.data.TvWebScrapper
import javax.inject.Inject
import javax.inject.Named

class ProgrammeRepository @Inject constructor(@Named("prefixUrl")val prefix: String, val channelList: List<String>,
                                              val tvWebScrapper: TvWebScrapper) {








}