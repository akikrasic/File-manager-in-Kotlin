package srb.akikrasic.apxu.forms.formforfilesearching.movethroughfilesystem

import java.io.File
import javax.swing.filechooser.FileSystemView

object StartingPaths {
    val myFile = "/home/aki/arhuPocetnePutanje"

    val startingPathsForEveryone = createStartingPaths()

    val monkeyOS = System.getProperty("os.name").uppercase().contains("WINDOWS")

    val shouldWeSavePaths = true

    fun createStartingPaths(): String {
        val startingPaths = "pocetnePutanje"
        val directory = FileSystemView.getFileSystemView().homeDirectory.absolutePath
        if (directory[directory.lastIndex] == '/' || directory[directory.lastIndex] == '\\') {
            return "${directory}${startingPaths}"
        }
        if (monkeyOS) {
            return "${directory}\\${startingPaths}"
        }
        return "${directory}/${startingPaths}"
    }

    fun loadStartingPaths(): List<String> {
        val startingPathsForAllFile = File(startingPathsForEveryone)
        if (startingPathsForAllFile.exists()) {
            return startingPathsForAllFile.readLines()
        }
        val myStartingPathsFile = File(myFile)
        if (myStartingPathsFile.exists()) {
            return myStartingPathsFile.readLines()
        }
        if (monkeyOS) {
            return listOf("C:\\")
        }
        return listOf(FileSystemView.getFileSystemView().homeDirectory.absolutePath)

    }

    fun saveStartingPathsForNextStart(list: List<String>) {
        if (shouldWeSavePaths) {
            val startingPathsForEveryoneFile = File(startingPathsForEveryone)
            val writerInFile = startingPathsForEveryoneFile.writer()
            list.forEach {
                writerInFile.write("${it}\n")
            }
            writerInFile.flush()
        }
    }

}