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
        return listOf("tvn","tvn-7","polsat","polsat-2","tvp-1","tvp-2","cartoon-network","discovery-channel",
            "tv-4","tv-6","disney-xd","tvp-sport","eleven-sports-1-hd")
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
            "https://i.pinimg.com/originals/92/a9/b4/92a9b4a00758ee8c9086ee661fb54830.jpg",
            "https://lh3.googleusercontent.com/proxy/OSOYX2heTwVeIC4HWJthD6Ngmh8WBxQiFR6l37FD_XN9JudcnnDEdDefYss0jIOcJTlMjMC7mt8AOjnaq0oIOT-Xft4tdkyrDedfpL5-E1By1u29TeyqbqhragOrEF-MbDEQ",
            "https://upload.wikimedia.org/wikipedia/commons/thumb/e/e0/TV4_pol.svg/1200px-TV4_pol.svg.png",
            "https://ocdn.eu/images/program-tv/ODM7MDA_/4d1b5527275ab44206ab4faa0e3e775b.png",
            "https://ocdn.eu/images/program-tv/ZDE7MDA_/4b0ebcc101ee8cb20824447bb42ec3e3.png",
            "https://lh3.googleusercontent.com/RqHWT4Y0B3SyJAB3KD6wwoDSEoeRAS7vR4NgquqqKqsy95QcS7NwgrqXIVMB8mDBnz-b",
            "https://pbs.twimg.com/profile_images/1013722580182863872/iazLEZLR_400x400.jpg")
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