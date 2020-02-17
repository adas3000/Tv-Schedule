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
        return listOf(
            "https://ocdn.eu/images/program-tv/ZjM7MDA_/6a8934d3c18b5168d00652440a3bd491.png",
            "https://ocdn.eu/program-tv/adm/cc7229d283fbe63aa3d33a9a9125d513137fd2289638f84cfd6285da27516be7",
            "https://www.dropbox.com/s/glmjpxc3u2not12/channel_logo_03.png?dl=1",
            "https://upload.wikimedia.org/wikipedia/commons/thumb/8/85/Polsat2_Logo.svg/1200px-Polsat2_Logo.svg.png",
            "https://ocdn.eu/images/program-tv/YTQ7MDA_/e068fa9c5e810071e10eb3a5eeb6e6f8.png",
            "https://ocdn.eu/images/program-tv/Y2Q7MDA_/7522e93676c43620309791346da88ece.png",
            "https://www.dropbox.com/s/4vellphu7o8gyob/channel_logo_02.png?dl=1",
            "https://www.dropbox.com/s/zfjplpzz9k1udlv/channel_logo_01.png?dl=1")
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