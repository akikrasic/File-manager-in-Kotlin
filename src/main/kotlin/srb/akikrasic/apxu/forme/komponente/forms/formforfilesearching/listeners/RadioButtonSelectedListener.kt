package srb.akikrasic.apxu.forme.komponente.forms.formforfilesearching.listeners

import srb.akikrasic.apxu.forme.komponente.forms.formforfilesearching.FormForFileSearchingInMultipleDirectories
import srb.akikrasic.apxu.forme.komponente.forms.formforfilesearching.panels.EnterTextForSearchingPanel
import srb.akikrasic.apxu.forme.komponente.forms.formforfilesearching.panels.RadioButtonsPanel
import java.awt.event.ItemEvent
import java.awt.event.ItemListener

class RadioButtonSelectedListener (val number:Int,
                                   val radioButtonsPanel: RadioButtonsPanel) :
    ItemListener{
    override fun itemStateChanged(e: ItemEvent?) {
        if( e?.stateChange== ItemEvent.SELECTED){
            radioButtonsPanel.selectionChanged(number)

        }
    }


}