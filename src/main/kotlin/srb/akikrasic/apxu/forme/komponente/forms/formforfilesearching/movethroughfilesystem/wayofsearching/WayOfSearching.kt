package srb.akikrasic.apxu.forme.komponente.forms.formforfilesearching.movethroughfilesystem.wayofsearching

import java.io.File

abstract class WayOfSearching(open val forSearch:String) {
    abstract fun fileNameMatches(file: File): Boolean
}