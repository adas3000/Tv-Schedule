package pl.tv.channellist


import junit.framework.TestCase.*
import org.jsoup.Jsoup
import org.jsoup.nodes.Document
import org.junit.Before
import org.junit.Test


class JsoupTest {


    private var url =
        "https://www.cyfrowypolsat.pl/redir/program-tv/program-tv-pionowy-single-channel.cp?chN=tvn"
    private lateinit var document: Document
    private val tableQuery = "table"
    private val displayTimeAndCategoryQuery = "span"
    private val displayTitleClass: String = "table.tableOgladaj"
    private val displaySecondTitleClass: String = "a.name"

    @Before
    fun setupDoc() {
        document = Jsoup.connect(url).get() as Document
    }


    @Test
    fun ifNotEmptyThenOk() {
        assertNotNull(document)
        assertTrue(url == document.location())
//
//        println(document.body().selectFirst("table").select("span").text())
//        println(document.body().selectFirst("table").select("table.tableOgladaj").select("a.name").text())


        val _2_3 = document.body().selectFirst(tableQuery).select(displayTimeAndCategoryQuery)
        val _1 = document.body().selectFirst(tableQuery).select(displayTitleClass)
            .select(displaySecondTitleClass)

        for(i in 0 until _2_3.size step 2){
            println(_2_3[i].text()+","+_2_3[i+1].text())
        }

        for(i in _1){
            println(i.text())
        }


        assertEquals(_2_3.size / 2, _1.size)
    }


}