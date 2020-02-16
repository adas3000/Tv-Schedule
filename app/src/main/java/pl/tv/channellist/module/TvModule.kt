package pl.tv.channellist.module

import dagger.Module
import dagger.Provides
import javax.inject.Named

@Module
class TvModule {


    @Provides
    @Named("prefixUrl")
    fun providePrefixUrl():String{
        return "https://www.cyfrowypolsat.pl/redir/program-tv/program-tv-pionowy-single-channel.cp?chN="
    }


    @Provides
    fun provideChanelList():List<String>{
        return listOf("tvn","tvn-7","polsat","polsat-2","tvp-1","tvp-2")
    }

    @Provides
    @Named("time")
    fun provideTvProgrammeDisplayTimeClass():String{
        return "span.time-resizable"
    }

    @Provides
    @Named("title")
    fun provideTvProgrammeDisplayTitleClass():String{
        return "class.name-resizable"
    }

    @Provides
    @Named("category")
    fun provideTvProgrammeDisplayCategoryClass():String{
        return "span.resizable"
    }

}