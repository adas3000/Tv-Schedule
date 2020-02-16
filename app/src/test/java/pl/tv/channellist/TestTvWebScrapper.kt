package pl.tv.channellist

import junit.framework.TestCase.*
import org.junit.Test
import pl.tv.channellist.model.data.TvWebScrapper


class TestTvWebScrapper {



    private val tvWebScrapper = TvWebScrapper("table","span",
        "table.tableOgladaj","a.name")

    @Test
    fun ifHoursThenOk(){

        val result =tvWebScrapper.scrapFromUrl("https://www.cyfrowypolsat.pl/redir/program-tv/program-tv-pionowy-single-channel.cp?chN=","tvn")

        assertNotNull(result)
        assertTrue(result.isNotEmpty())
    }




}