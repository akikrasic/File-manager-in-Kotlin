package srb.akikrasic.apxu.forms.formforfilesearching.movethroughfilesystem.wayofsearching

import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test
import java.io.File

class PutDotAnyAfterEveryLetterWayOfSearchingTest() {
    val forSearch = "a bcd"
    val p = PutDotAnyAfterEveryLetterWayOfSearching(forSearch)

    @Test
    fun testCreateRegex() {
        val expected = ".*A.*.*B.*C.*D.*"
        assertEquals(expected, p.regex.pattern)
    }

    @Test
    fun testRegex() {
        assertTrue { p.fileNameMatches(File("test asdf to be recreate during")) }
    }

    @Test
    fun testRegexFailing() {
        assertFalse(p.fileNameMatches(File("test")))
    }
}