package srb.akikrasic.apxu.forme.komponente.forms.formforfilesearching.panels
import srb.akikrasic.apxu.forme.komponente.forms.formforfilesearching.listeners.RadioButtonSelectedListener
import srb.akikrasic.apxu.forme.komponente.forms.util.GridBagConstraintsCreator
import java.awt.GridBagLayout
import javax.swing.ButtonGroup
import javax.swing.JPanel
import javax.swing.JRadioButton

class RadioButtonsPanel(val enterTextForSearchingPanel: EnterTextForSearchingPanel): JPanel() {
    val wholeStringRB = JRadioButton("Претражите са целим стрингом")
    val changeWhiteSpaceWithDotAnyRB = JRadioButton("Регекс претрага празан стринг у било шта")
    val putDotAnyAfterEveryLetterRB = JRadioButton("Регекс претрага било шта после сваког слова")
    val groupOfButtons = ButtonGroup()

    init{
        val c=GridBagConstraintsCreator.createGridBagConstraints()
        layout=GridBagLayout()
        groupOfButtons.add(wholeStringRB)
        groupOfButtons.add(changeWhiteSpaceWithDotAnyRB)
        groupOfButtons.add(putDotAnyAfterEveryLetterRB)
        wholeStringRB.isSelected=true

        add(wholeStringRB, c)
        c.gridx=1
        add(changeWhiteSpaceWithDotAnyRB, c)
        c.gridx=2
        add(putDotAnyAfterEveryLetterRB, c)
        wholeStringRB.addItemListener(RadioButtonSelectedListener(0, this ))
        changeWhiteSpaceWithDotAnyRB.addItemListener (RadioButtonSelectedListener(1,this))
        putDotAnyAfterEveryLetterRB.addItemListener (RadioButtonSelectedListener(2, this))
    }
    fun selectionChanged(number:Int){
        enterTextForSearchingPanel.changedWayOfSearching(number)
    }


}