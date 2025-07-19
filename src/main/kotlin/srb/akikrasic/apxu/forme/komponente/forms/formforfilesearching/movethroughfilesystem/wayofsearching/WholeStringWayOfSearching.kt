package srb.akikrasic.apxu.forme.komponente.forms.formforfilesearching.movethroughfilesystem.wayofsearching

import java.io.File

class WholeStringWayOfSearching(override val forSearch: String) : WayOfSearching(forSearch) {
    override fun fileNameMatches(file: File): Boolean = file.name.uppercase().contains(forSearch)
}