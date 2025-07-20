package srb.akikrasic.apxu.forms.formforfilesearching.listeners

import srb.akikrasic.apxu.fajlsistem.PocetnePutanje
import srb.akikrasic.apxu.forms.formforfilesearching.FormForFileSearchingInMultipleDirectories
import java.awt.event.WindowEvent
import java.awt.event.WindowListener

class SaveDirectoriesForNextStartListener(val formForFileSearchingInMultipleDirectories: FormForFileSearchingInMultipleDirectories) :
    WindowListener {
    override fun windowOpened(e: WindowEvent?) {

    }

    override fun windowClosing(e: WindowEvent?) {
        PocetnePutanje.sacuvajtePutanjeZaSledeciPut(formForFileSearchingInMultipleDirectories.showDirectoriesPanelsList.map { it.kretanjeKrozDirektorijum.currentDirectoryString })
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