package srb.akikrasic.apxu.forms.formforfilesearching.listeners

import srb.akikrasic.apxu.forms.formforfilesearching.FormForFileSearchingInMultipleDirectories
import srb.akikrasic.apxu.forms.formforfilesearching.movethroughfilesystem.StartingPaths
import java.awt.event.WindowEvent
import java.awt.event.WindowListener

class SaveDirectoriesForNextStartListener(val formForFileSearchingInMultipleDirectories: FormForFileSearchingInMultipleDirectories) :
    WindowListener {
    override fun windowOpened(e: WindowEvent?) {

    }

    override fun windowClosing(e: WindowEvent?) {
        StartingPaths.saveStartingPathsForNextStart(formForFileSearchingInMultipleDirectories.showDirectoriesPanelsList.map { it.kretanjeKrozDirektorijum.currentDirectoryString })
    }

    override fun windowClosed(e: WindowEvent?) {

    }

    override fun windowIconified(e: WindowEvent?) {
    }

    override fun windowDeiconified(e: WindowEvent?) {
    }

    override fun windowActivated(e: WindowEvent?) {
    }

    override fun windowDeactivated(e: WindowEvent?) {
    }

}