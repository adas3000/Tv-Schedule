package pl.tv.channellist.model.repository

import javax.inject.Inject

class ChannelRepository @Inject constructor(val prefix: String, val channelList: List<String>) {



}