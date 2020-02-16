package pl.tv.channellist.model.repository

import javax.inject.Inject
import javax.inject.Named

class ChannelRepository @Inject constructor(@Named("prefixUrl")val prefix: String, val channelList: List<String>) {



}