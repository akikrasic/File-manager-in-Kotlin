package srb.akikrasic.apxu.forme.komponente.forms.formforfilesearching.movethroughfilesystem

import srb.akikrasic.apxu.forme.komponente.forms.formforfilesearching.movethroughfilesystem.wayofsearching.WayOfSearching
import srb.akikrasic.apxu.forme.komponente.forms.formforfilesearching.movethroughfilesystem.wayofsearching.WholeStringWayOfSearching
import java.io.File

class MovingThroughFileSystem(var currentDirectoryString: String = "/") {
    val comparator = ComparatorOfFilesForShowingInList()
    var currentDirectoryFile = File(currentDirectoryString)
    var currentWayOfSearching: WayOfSearching = WholeStringWayOfSearching("")

    fun setCurrentDirectoryStringAndFileFromFile(directory: File) {
        currentDirectoryString = directory.name
        currentDirectoryFile = directory
    }

    fun returnToPreviousDirectoryAndReturnHisFiles(): List<File> {
        if (currentDirectoryString != "/") {
            setCurrentDirectoryStringAndFileFromFile(currentDirectoryFile.parentFile ?: File("/"))
        }
        return search(this.currentWayOfSearching)
    }

    fun goToDirectoryAndReturnItsFiles(directory: File): List<File> {
        setCurrentDirectoryStringAndFileFromFile(directory)
        return search(this.currentWayOfSearching)
    }

    fun getAllFilesFromCurrentDirectorySorted(): List<File> {
        return currentDirectoryFile.listFiles()?.sortedWith(comparator) ?: listOf()
    }

    fun currentAbsolutePath() = currentDirectoryFile.absolutePath

    fun search(wayOfSearching: WayOfSearching): List<File> {
        currentWayOfSearching = wayOfSearching
        return if (!wayOfSearching.shouldNotSearch()) {
            currentDirectoryFile.listFiles()?.filter {
                wayOfSearching.fileNameMatches(it)
            }?.sortedWith(comparator) ?: listOf()
        } else {
            getAllFilesFromCurrentDirectorySorted()
        }
    }

}