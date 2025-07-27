package srb.akikrasic.apxu.forms.formforfilesearching.movethroughfilesystem.wayofsearching

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

class ChangeWhitespaceWithDotAnyWayOfSearchingTest {
    val forSearch = "abc def"
    val c = ChangeWhitespaceWithDotAnyWayOfSearching(forSearch)


    @Test
    fun testCreateRegex() {
        assertEquals(".*ABC.*DEF.*", c.regex.pattern)
    }
}