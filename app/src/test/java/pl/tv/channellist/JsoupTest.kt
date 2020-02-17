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

        val elements = document.body().selectFirst(tableQuery).select(displayTimeAndCategoryQuery)
        val elements1 = document.body().selectFirst(tableQuery).select(displayTitleClass)
            .select(displaySecondTitleClass)

        assertEquals(elements.size / 2, elements1.size)
    }

    @Test
    fun booleanTest(){
        assertEquals(false,"false".toBoolean())
        assertEquals(false,"FALSE".toBoolean())
        assertEquals(false,"FAlSe".toBoolean())
    }

}