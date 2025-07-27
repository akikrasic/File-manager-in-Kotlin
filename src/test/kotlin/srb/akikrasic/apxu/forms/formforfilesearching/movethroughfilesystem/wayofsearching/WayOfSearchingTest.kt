package srb.akikrasic.apxu.forms.formforfilesearching.movethroughfilesystem.wayofsearching

import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.Test

class WayOfSearchingTest {

    @Test
    fun shouldNotSearch() {
        val c = PutDotAnyAfterEveryLetterWayOfSearching("")

        assertTrue(c.shouldNotSearch())

    }

}