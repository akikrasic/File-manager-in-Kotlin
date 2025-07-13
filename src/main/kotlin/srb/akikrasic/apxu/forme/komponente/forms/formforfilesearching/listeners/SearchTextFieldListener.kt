package srb.akikrasic.apxu.forme.komponente.forms.formforfilesearching.listeners

import srb.akikrasic.apxu.forme.komponente.forms.formforfilesearching.panels.EnterTextForSearchingPanel
import java.awt.event.KeyEvent
import java.awt.event.KeyListener

class SearchTextFieldListener(val enterTextForSearchingPanel: EnterTextForSearchingPanel): KeyListener {
    override fun keyTyped(e: KeyEvent?) {
    }

    override fun keyPressed(e: KeyEvent?) {
    }

    override fun keyReleased(e: KeyEvent?) {
        val s = enterTextForSearchingPanel.textForSearching()
        if(s!=""){

            enterTextForSearchingPanel.formForFileSearchingInMultipleDirectories.searchRegex(Regex(s, RegexOption.IGNORE_CASE))
        }
        else{
            enterTextForSearchingPanel.formForFileSearchingInMultipleDirectories.emptyString()
        }
    }

}