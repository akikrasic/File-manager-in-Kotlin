package srb.akikrasic.apxu.forme.komponente.forms.formforfilesearching.listeners

import srb.akikrasic.apxu.forme.komponente.forms.formforfilesearching.panels.ShowDirectoryContentInList
import java.awt.Point
import java.awt.event.MouseEvent
import java.awt.event.MouseListener
import java.io.File
import javax.swing.JMenuItem
import javax.swing.JPopupMenu

class ShowDirectoryContentInListMouseListener(val showDirectoryContentInList: ShowDirectoryContentInList): MouseListener {

    override fun mouseClicked(e: MouseEvent?) {

        if(e?.clickCount==2) {
            showDirectoryContentInList.fileIsChosenInList()
            return
        }

    }

    override fun mousePressed(e: MouseEvent?) {

        if( e?.isPopupTrigger?:false){
            showDirectoryContentInList.showPopupMenu(e.x, e.y)
        }
    }

    override fun mouseReleased(e: MouseEvent?) {

    }

    override fun mouseEntered(e: MouseEvent?) {
    }

    override fun mouseExited(e: MouseEvent?) {
    }

}