package srb.akikrasic.apxu.forme.komponente.forms.formforfilesearching.panels

import srb.akikrasic.apxu.forme.komponente.ListaRenderer
import srb.akikrasic.apxu.forme.komponente.forms.formforfilesearching.FormForFileSearchingInMultipleDirectories
import srb.akikrasic.apxu.forme.komponente.forms.formforfilesearching.listeners.ShowDirectoryContentInListMouseListener
import srb.akikrasic.apxu.forme.komponente.forms.formforfilesearching.models.DirectoryListModel
import srb.akikrasic.apxu.forme.komponente.forms.formforfilesearching.movethroughfilesystem.MovingThroughFileSystem
import srb.akikrasic.apxu.forme.komponente.forms.formforfilesearching.movethroughfilesystem.wayofsearching.WayOfSearching
import srb.akikrasic.apxu.forme.komponente.forms.util.GridBagConstraintsCreator
import java.awt.*
import java.awt.event.ActionListener
import java.io.File
import javax.swing.*

class ShowDirectoryContentInList(
    val formFileSearchingInMultipleDirectories: FormForFileSearchingInMultipleDirectories,
    putanja: String = "/"
) : JPanel() {
    val currentDirectoryTitleLabel = JLabel("Тренутни директоријум")
    val showCurrentDirectoryLabel = JLabel(putanja)
    val backJButton = JButton("Назад")
    val listForShowingDirectoryContent = JList<File>()
    val scrollList = JScrollPane(listForShowingDirectoryContent)

    val kretanjeKrozDirektorijum = MovingThroughFileSystem(putanja)

    init {
        layout = GridBagLayout()
        val c = GridBagConstraintsCreator.createGridBagConstraints()
        c.insets = Insets(5, 5, 5, 5)
        c.fill = GridBagConstraints.BOTH
        c.weightx = 1.0

        putListOfFilesIntoJListForShowing(kretanjeKrozDirektorijum.getAllFilesFromCurrentDirectorySorted())
        listForShowingDirectoryContent.cellRenderer = ListaRenderer()
        listForShowingDirectoryContent.addMouseListener(ShowDirectoryContentInListMouseListener(this))

        backJButton.addActionListener(ActionListener { goToPreviousDirectory() })
        listOf(currentDirectoryTitleLabel, showCurrentDirectoryLabel, backJButton)
            .forEach {
                add(it, c)
                c.gridy++
            }
        c.weighty = 0.8
        add(scrollList, c)

    }

    fun fileIsChosenInList() {
        val f = listForShowingDirectoryContent.selectedValue ?: File("/")

        if (f.isDirectory) {
            openDirectoryAndShow(f)
        } else {
            Desktop.getDesktop().open(f)
        }
    }

    fun showPopupMenu(x: Int, y: Int) {

        val menu = JPopupMenu()
        val item = JMenuItem("Додајте")
        val el = listForShowingDirectoryContent.model.getElementAt(
            listForShowingDirectoryContent.locationToIndex(
                Point(x, y)
            )
        )
        menu.add(item)
        item.addActionListener { event ->
            formFileSearchingInMultipleDirectories.addNewDirectory(el.absolutePath)
        }
        if (el.isDirectory) {
            menu.show(listForShowingDirectoryContent, x, y)
        }
    }

    private fun putListOfFilesIntoJListForShowing(l: List<File>) {
        listForShowingDirectoryContent.model = DirectoryListModel(l)
        showCurrentDirectoryLabel.text = kretanjeKrozDirektorijum.currentAbsolutePath()
    }

    private fun goToPreviousDirectory() {
        putListOfFilesIntoJListForShowing(kretanjeKrozDirektorijum.returnToPreviousDirectoryAndReturnHisFiles())
    }

    fun search(wayOfSearching: WayOfSearching) {
        putListOfFilesIntoJListForShowing(kretanjeKrozDirektorijum.search(wayOfSearching))
    }

    fun openDirectoryAndShow(directory: File) {
        putListOfFilesIntoJListForShowing(kretanjeKrozDirektorijum.goToDirectoryAndReturnItsFiles(directory))
    }


}