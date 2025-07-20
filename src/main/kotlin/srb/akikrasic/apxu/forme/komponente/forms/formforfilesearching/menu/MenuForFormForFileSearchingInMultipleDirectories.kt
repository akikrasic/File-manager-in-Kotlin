package srb.akikrasic.apxu.forme.komponente.forms.formforfilesearching.menu

import srb.akikrasic.apxu.forme.komponente.forms.formforfilesearching.FormForFileSearchingInMultipleDirectories
import srb.akikrasic.apxu.language.LanguageInterface
import srb.akikrasic.apxu.language.LanguageService
import javax.swing.JFileChooser
import javax.swing.JMenu
import javax.swing.JMenuBar
import javax.swing.JMenuItem

class MenuForFormForFileSearchingInMultipleDirectories(val formForFileSearchingInMultipleDirectories: FormForFileSearchingInMultipleDirectories) :
    JMenuBar(), LanguageInterface {
    val firstMenu = JMenu("")
    val addNewDirectoryForSearchButton = JMenuItem("")
    val languagesMenu = JMenu("")
    val serbianLanguage = JMenuItem("")
    val englishLanguage = JMenuItem("")

    init {
        add(firstMenu)
        firstMenu.add(addNewDirectoryForSearchButton)
        addNewDirectoryForSearchButton.addActionListener { e ->
            val dialog = JFileChooser()
            dialog.fileSelectionMode = JFileChooser.DIRECTORIES_ONLY
            val result = dialog.showOpenDialog(null)
            if (result == JFileChooser.APPROVE_OPTION) {

                formForFileSearchingInMultipleDirectories.addNewDirectory(dialog.selectedFile.absolutePath)
            }
        }
        add(languagesMenu)
        languagesMenu.add(serbianLanguage)
        languagesMenu.add(englishLanguage)
        serbianLanguage.addActionListener {
            setLanguageAndRepaint(LanguageService::setSerbianLanguage)
        }
        englishLanguage.addActionListener {
            setLanguageAndRepaint(LanguageService::setEnglishLanguage)
        }
        translateComponent()
    }

    fun setLanguageAndRepaint(languageTranslationFunction: () -> Unit) {
        languageTranslationFunction()
        formForFileSearchingInMultipleDirectories.translateComponent()
        formForFileSearchingInMultipleDirectories.revalidate()
        formForFileSearchingInMultipleDirectories.repaint()
    }

    override fun setTranslations(translations: Map<String, String>) {
        firstMenu.text = translations["firstMenu"]
        addNewDirectoryForSearchButton.text = translations["addNewDirectoryForSearchButton"]
        languagesMenu.text = translations["languagesMenu"]
        serbianLanguage.text = translations["serbianLanguage"]
        englishLanguage.text = translations["englishLanguage"]
    }


}