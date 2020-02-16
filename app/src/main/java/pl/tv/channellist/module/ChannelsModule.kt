package pl.tv.channellist.module

import dagger.Module
import dagger.Provides

@Module
class ChannelsModule {


    @Provides
    fun providePrefixUrl():String{
        return "https://www.cyfrowypolsat.pl/redir/program-tv/program-tv-pionowy-single-channel.cp?chN="
    }


    @Provides
    fun provideChanelList():List<String>{
        return listOf("tvn","tvn-7","polsat","polsat-2","tvp-1","tvp-2")
    }



}