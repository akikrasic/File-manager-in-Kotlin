package srb.akikrasic.apxu.forme.komponente.novi

import srb.akikrasic.apxu.forme.komponente.forms.formforfilesearching.FormForFileSearchingInMultipleDirectories
import java.awt.GridBagConstraints
import java.awt.GridBagLayout
import java.awt.Insets
import java.awt.event.KeyEvent
import java.awt.event.KeyListener
import javax.swing.JLabel
import javax.swing.JPanel
import javax.swing.JTextField

class NoviPanelZaPretragu(forma: FormForFileSearchingInMultipleDirectories): JPanel() {
    val labela = JLabel("Унсите текст за претрагу у директоријумима:")
    val polje = JTextField()
    init{
        layout= GridBagLayout()
        val c = GridBagConstraints()
        c.fill= GridBagConstraints.BOTH
        c.weightx=1.0
        c.insets = Insets(10,10,10,10)
        c.gridx= 0
        c.gridy=0
        add(labela, c)
        c.gridy=1
        add(polje, c)
        polje.addKeyListener(object: KeyListener {
            override fun keyTyped(e: KeyEvent?) {
            }

            override fun keyPressed(e: KeyEvent?) {
            }

            override fun keyReleased(e: KeyEvent?) {
               val s = vratiteTekst()
                 if(s!=""){
                    // forma.pretraga(s)
                    forma.searchRegex(Regex(s, RegexOption.IGNORE_CASE))
                 }
                else{
                    forma.emptyString()
                }
            }

        })

    }
    private fun vratiteTekst() = ".*${(polje.text?.uppercase()?:"").trim().replace(" ",".*")}.*"

    fun izvuciteStringZaPretragu()= vratiteTekst()

    fun izvuciteRegexZaPretragu() = Regex(vratiteTekst(), RegexOption.IGNORE_CASE )

}