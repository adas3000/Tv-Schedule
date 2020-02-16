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
        return listOf("https://upload.wikimedia.org/wikipedia/commons/1/1d/TVN_logo.svg",
            "https://vignette.wikia.nocookie.net/logaekranowe/images/8/8e/TVN_Mourning_1997-2001_o-sb.png/revision/latest/scale-to-width-down/340?cb=20181031204220&path-prefix=pl",
            "https://r-scale-48.dcs.redcdn.pl/scale/o2/tvn/web-content/m/p123/i/9597353e41e6957b5e7aa79214fcb256/69956f7b-0887-4edd-a7be-1d0232bae72e.jpg?type=1&srcmode=3&srcx=1%2F2&srcy=0%2F1&srcw=960&srch=540&dstw=960&dsth=540&quality=85",
            "",
            "",
            "")
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