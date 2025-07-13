package srb.akikrasic.apxu.forme.komponente.forms.formforfilesearching.panels

import srb.akikrasic.apxu.forme.komponente.forms.formforfilesearching.FormForFileSearchingInMultipleDirectories
import srb.akikrasic.apxu.forme.komponente.forms.formforfilesearching.listeners.SearchTextFieldListener
import srb.akikrasic.apxu.forme.komponente.forms.util.GridBagConstraintsCreator
import java.awt.GridBagConstraints
import java.awt.GridBagLayout
import java.awt.Insets
import javax.swing.JLabel
import javax.swing.JPanel
import javax.swing.JTextField

class EnterTextForSearchingPanel(val formForFileSearchingInMultipleDirectories: FormForFileSearchingInMultipleDirectories): JPanel() {
    val searchTextLabel = JLabel("Унсите текст за претрагу у директоријумима:")
    val searchTextField = JTextField()
    init{
        layout= GridBagLayout()
        val c = GridBagConstraintsCreator.createGridBagConstraints()
        c.fill= GridBagConstraints.BOTH
        c.weightx=1.0
        c.insets = Insets(10, 10, 10, 10)

        add(searchTextLabel, c)
        c.gridy=1
        add(searchTextField, c)
        searchTextField.addKeyListener(SearchTextFieldListener(this))

    }
    fun textForSearching() = ".*${(searchTextField.text?.uppercase()?:"").trim().replace(" ",".*")}.*"

    fun takeTextForSearching()= textForSearching()

    fun takeRegexForSearching() = Regex(textForSearching(), RegexOption.IGNORE_CASE )

}