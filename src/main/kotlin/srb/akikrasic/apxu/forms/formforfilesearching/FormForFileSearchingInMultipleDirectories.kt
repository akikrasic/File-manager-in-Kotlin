package srb.akikrasic.apxu.forms.formforfilesearching

import srb.akikrasic.apxu.fajlsistem.PocetnePutanje
import srb.akikrasic.apxu.forms.Form
import srb.akikrasic.apxu.forms.formforfilesearching.listeners.SaveDirectoriesForNextStartListener
import srb.akikrasic.apxu.forms.formforfilesearching.menu.MenuForFormForFileSearchingInMultipleDirectories
import srb.akikrasic.apxu.forms.formforfilesearching.movethroughfilesystem.wayofsearching.WayOfSearching
import srb.akikrasic.apxu.forms.formforfilesearching.panels.EnterTextForSearchingPanel
import srb.akikrasic.apxu.forms.formforfilesearching.panels.ShowDirectoryContentInList
import srb.akikrasic.apxu.forms.util.GridBagConstraintsCreator

import java.awt.GridBagConstraints
import java.awt.GridBagLayout
import javax.swing.JPanel
import javax.swing.JSplitPane

class FormForFileSearchingInMultipleDirectories : Form() {

    val enterTextForSearchingPanel = EnterTextForSearchingPanel(this)
    val showDirectoriesPanelsList = mutableListOf<ShowDirectoryContentInList>()
    val panelForJSplitPanes = JPanel()
    var screenWidth = 0
    val menuForFormForFileSearchingInMultipleDirectories = MenuForFormForFileSearchingInMultipleDirectories(this)

    init {
        translateComponent()
        showDirectoriesPanelsList.addAll(PocetnePutanje.ucitajtePocetnePutanje().map {
            ShowDirectoryContentInList(
                this,
                it
            )
        })

        //ajde pisem sve opet cisto da se podsetim iako vec imam taj kod skoro sve
        this.defaultCloseOperation = EXIT_ON_CLOSE
        val screenDimensions = toolkit.screenSize.size
        screenWidth = screenDimensions.width
        setSize(screenWidth, screenDimensions.height)
        isVisible = true

        addPanelsToForm()
        addMenu()
        this.addWindowListener(SaveDirectoriesForNextStartListener(this))
    }

    private fun addPanelsToForm() {
        contentPane.layout = GridBagLayout()
        val c = GridBagConstraintsCreator.createGridBagConstraints()
        c.weightx = 0.2
        c.weighty = 0.2
        c.fill = GridBagConstraints.FIRST_LINE_START
        contentPane.add(enterTextForSearchingPanel)
        c.weighty = 0.8
        c.gridy = 1
        c.fill = GridBagConstraints.BOTH
        //c.weightx= 1.0/noviPrikaziDirektorijumaLista.size
        c.weightx = 1.0
//        noviPrikaziDirektorijumaLista.forEach{
//            contentPane.add(it, c)
//            c.gridx++
//        }
        contentPane.add(panelForJSplitPanes, c)
        panelForJSplitPanes.layout = GridBagLayout()
        addDirectoriesToJSplitPane(screenWidth)
    }

    private fun addMenu() {
        jMenuBar = menuForFormForFileSearchingInMultipleDirectories
    }

    fun addNewDirectory(pathToNewDirectory: String) {

        val newDirectoryForShowing =
            ShowDirectoryContentInList(this@FormForFileSearchingInMultipleDirectories, pathToNewDirectory)
        showDirectoriesPanelsList.add(newDirectoryForShowing)
        newDirectoryForShowing.search(enterTextForSearchingPanel.wayOfSearching)
        panelForJSplitPanes.removeAll()
        addDirectoriesToJSplitPane(screenWidth)
        panelForJSplitPanes.repaint()
        panelForJSplitPanes.revalidate()
    }

    private fun createNewJSplitPane(widthOfThePane: Int): JSplitPane {
        val pane = JSplitPane()
        pane.isOneTouchExpandable = true
        pane.dividerLocation = widthOfThePane
        return pane
    }

    fun addDirectoriesToJSplitPane(widthOfJSplitPane: Int) {
        val numberOfDirectories = showDirectoriesPanelsList.size
        val widthOfSingleDirectoryInJSplitPane = widthOfJSplitPane / numberOfDirectories
        val firstPane = createNewJSplitPane(widthOfSingleDirectoryInJSplitPane)
        var currentPane = firstPane

        val c = GridBagConstraintsCreator.createGridBagConstraints()
        c.weightx = 1.0
        c.weighty = 1.0
        c.fill = GridBagConstraints.BOTH

        when (numberOfDirectories) {
            1 -> {
                panelForJSplitPanes.add(showDirectoriesPanelsList[0], c)
                return
            }

            2 -> {
                firstPane.leftComponent = showDirectoriesPanelsList[0]
                firstPane.rightComponent = showDirectoriesPanelsList[1]
            }

            else -> {
                for (i in 0..showDirectoriesPanelsList.lastIndex - 2) {
                    currentPane.leftComponent = showDirectoriesPanelsList[i]
                    val noviSplitPane = createNewJSplitPane(widthOfSingleDirectoryInJSplitPane)
                    currentPane.rightComponent = noviSplitPane
                    currentPane = noviSplitPane
                }
                currentPane.leftComponent = showDirectoriesPanelsList[showDirectoriesPanelsList.lastIndex - 1]
                currentPane.rightComponent = showDirectoriesPanelsList[showDirectoriesPanelsList.lastIndex]
            }
        }


        panelForJSplitPanes.add(firstPane, c)
        //  contentPane.add(firstPane, c)
    }

    fun search(wayOfSearching: WayOfSearching) {
        showDirectoriesPanelsList.forEach {
            it.search(wayOfSearching)
        }
    }

    override fun setTranslations(translations: Map<String, String>) {
        title = translations["title"] ?: "kompir"
        enterTextForSearchingPanel.translateComponent()
        showDirectoriesPanelsList.forEach { it.translateComponent() }
        menuForFormForFileSearchingInMultipleDirectories.translateComponent()
    }



}