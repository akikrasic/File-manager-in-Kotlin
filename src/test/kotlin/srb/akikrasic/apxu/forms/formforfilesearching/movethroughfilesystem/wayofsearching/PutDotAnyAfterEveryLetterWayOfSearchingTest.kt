package srb.akikrasic.apxu.forms.formforfilesearching.movethroughfilesystem.wayofsearching

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.Test
import java.io.File

class PutDotAnyAfterEveryLetterWayOfSearchingTest {
    val forSearch = "abcd"
    val p = PutDotAnyAfterEveryLetterWayOfSearching(forSearch)

    @Test
    fun testcreateRegex() {
        val expected = ".*a.*b.*c.*d.*"
        assertEquals(expected, p.regex.pattern)
    }

    @Test
    fun testRegex() {
        assertTrue { p.fileNameMatches(File("test asdf to be recreate during")) }
    }

    @Test
    fun testRegexFailing() {

    }
}