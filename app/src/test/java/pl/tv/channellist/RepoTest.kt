package pl.tv.channellist

import org.junit.Test
import junit.framework.TestCase.*
import pl.tv.channellist.model.data.TvWebScrapper
import pl.tv.channellist.model.repository.ProgrammeRepository

class RepoTest {


    val programmeRepository = ProgrammeRepository("https://www.cyfrowypolsat.pl/redir/program-tv/program-tv-pionowy-single-channel.cp?chN=",
        listOf("tvn","polsat","tvn-7","polsat-2"), listOf(), TvWebScrapper("table","span",
            "table.tableOgladaj","a.name"))

    @Test
    fun doFindProgrammesTest(){
        val programmes = programmeRepository.doFindProgrammes()
        assertTrue(programmes.isNotEmpty())

        for(i in programmes){
            println(i.name+","+i.movieList.joinToString(", "))
        }


    }


}