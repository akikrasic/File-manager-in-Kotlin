package srb.akikrasic.apxu.forms.formforfilesearching.movethroughfilesystem.wayofsearching

import java.io.File

abstract class WayOfSearching(open val forSearch: String) {
    abstract fun fileNameMatches(file: File): Boolean
    fun shouldNotSearch() = forSearch.trim() == ""
}