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
    @Named("channelList")
    fun provideChanelList():List<String>{
        return listOf("tvn","tvn-7","polsat","polsat-2","tvp-1","tvp-2")
    }

    @Provides
    @Named("logoList")
    fun provideLogoList():List<String>{
        return listOf()
    }

    @Provides
    @Named("nextTitleSelection")
    fun provideDisplayTitleNextSelection():String{
        return "table.tableOgladaj"
    }

    @Provides
    @Named("lastTitleSelection")
    fun provideDisplayTitleLastSelection():String{
        return "a.name"
    }

    @Provides
    @Named("firstQuery")
    fun provideFirstQuerySelection():String{
        return "table"
    }

    @Provides
    @Named("lastTimeAndCategorySelection")
    fun provideTimeAndCategoryQueryNextSelection():String{
        return "span"
    }








}