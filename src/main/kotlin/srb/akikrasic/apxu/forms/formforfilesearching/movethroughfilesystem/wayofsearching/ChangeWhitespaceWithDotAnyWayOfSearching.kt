package srb.akikrasic.apxu.forms.formforfilesearching.movethroughfilesystem.wayofsearching

import java.io.File

open class ChangeWhitespaceWithDotAnyWayOfSearching(override val forSearch: String) : WayOfSearching(forSearch) {
    val regex = createRegex()
    override fun fileNameMatches(file: File): Boolean = regex.containsMatchIn(file.name.uppercase())
    open fun createRegex(): Regex = Regex(".*${(forSearch.uppercase()).trim().replace(" ", ".*")}.*")

}