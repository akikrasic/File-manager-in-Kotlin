package srb.akikrasic.apxu.forme.komponente.forms.formforfilesearching

import srb.akikrasic.apxu.fajlsistem.PocetnePutanje
import srb.akikrasic.apxu.forme.jezici.JezikServis
import srb.akikrasic.apxu.forme.jezici.PromenaJezika
import srb.akikrasic.apxu.forme.komponente.forms.Form
import srb.akikrasic.apxu.forme.komponente.forms.formforfilesearching.listeners.SaveDirectoriesForNextStartListener
import srb.akikrasic.apxu.forme.komponente.forms.formforfilesearching.movethroughfilesystem.wayofsearching.WayOfSearching
import srb.akikrasic.apxu.forme.komponente.forms.util.GridBagConstraintsCreator
import srb.akikrasic.apxu.forme.komponente.forms.formforfilesearching.panels.EnterTextForSearchingPanel
import srb.akikrasic.apxu.forme.komponente.novi.NoviPrikazDirektorijumaLista
import java.awt.GridBagConstraints
import java.awt.GridBagLayout
import javax.swing.JFileChooser
import javax.swing.JFrame
import javax.swing.JMenu
import javax.swing.JMenuBar
import javax.swing.JMenuItem
import javax.swing.JPanel
import javax.swing.JSplitPane

class FormForFileSearchingInMultipleDirectories: Form(), PromenaJezika {

    val enterTextForSearchingPanel = EnterTextForSearchingPanel(this)
    val showDirectoriesPanelsList = mutableListOf<NoviPrikazDirektorijumaLista>()
    val panelForJSplitPanes = JPanel()
    var screenWidth = 0

    override fun loadLanguageIntoTheForm(map: Map<String, String>) {
        title=map["naslov"]
    }

    override fun returnLanguageMap(): Map<String, String>  = JezikServis.odabraniJezik().forma2()


    init{

        showDirectoriesPanelsList.addAll(PocetnePutanje.ucitajtePocetnePutanje().map {
            NoviPrikazDirektorijumaLista(
                this,
                it
            )
        })
        //ajde pisem sve opet cisto da se podsetim iako vec imam taj kod skoro sve
        this.defaultCloseOperation= JFrame.EXIT_ON_CLOSE
        val screenDimensions = toolkit.screenSize.size
         screenWidth = screenDimensions.width
        setSize(screenWidth, screenDimensions.height)
        isVisible = true

        addPanelsToForm()
        addMenu()
        this.addWindowListener(SaveDirectoriesForNextStartListener(this))
    }
    private fun addPanelsToForm(){
        contentPane.layout= GridBagLayout()
        val c = GridBagConstraintsCreator.createGridBagConstraints()
        c.weightx=0.2
        c.weighty=0.2
        c.fill = GridBagConstraints.FIRST_LINE_START
        contentPane.add(enterTextForSearchingPanel)
        c.weighty=0.8
        c.gridy=1
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
        val menuBar = JMenuBar()
        val meni = JMenu("Мени")
        menuBar.add(meni)
        val addNewDirectoryForSearchButton = JMenuItem("Додајте")
        meni.add(addNewDirectoryForSearchButton)
        addNewDirectoryForSearchButton.addActionListener { e ->
            val dialog = JFileChooser()

            dialog.fileSelectionMode = JFileChooser.DIRECTORIES_ONLY
            val result = dialog.showOpenDialog(null)
            if( result== JFileChooser.APPROVE_OPTION){
               // println(dialog.selectedFile)
               addNewDirectory(dialog.selectedFile.absolutePath)
            }

        }

        jMenuBar = menuBar
    }
    fun addNewDirectory(pathToNewDirectory:String){

        val newDirectoryForShowing  =
            NoviPrikazDirektorijumaLista(this@FormForFileSearchingInMultipleDirectories, pathToNewDirectory)
        showDirectoriesPanelsList.add(newDirectoryForShowing)
        //novi.pretraga(noviPanelZaPretragu.izvuciteStringZaPretragu())
        newDirectoryForShowing.pretragaRegex(enterTextForSearchingPanel.takeRegexForSearching())
        panelForJSplitPanes.removeAll()
        addDirectoriesToJSplitPane(screenWidth)
        panelForJSplitPanes.repaint()
        panelForJSplitPanes.revalidate()
    }

    private fun  createNewJSplitPane(widthOfThePane:Int): JSplitPane {
        val pane = JSplitPane()
        pane.isOneTouchExpandable = true
        pane.dividerLocation= widthOfThePane
        return pane
    }

    fun addDirectoriesToJSplitPane(widthOfJSplitPane:Int){
        val numberOfDirectories = showDirectoriesPanelsList.size
        val widthOfSingleDirectoryInJSplitPane = widthOfJSplitPane/numberOfDirectories
        val firstPane = createNewJSplitPane(widthOfSingleDirectoryInJSplitPane)
        var currentPane = firstPane

        val c = GridBagConstraintsCreator.createGridBagConstraints()
        c.weightx=1.0
        c.weighty=1.0
        c.fill = GridBagConstraints.BOTH

        when(numberOfDirectories){
             1->{
                 panelForJSplitPanes.add(showDirectoriesPanelsList[0], c)
                 return
             }
            2->{
                firstPane.leftComponent = showDirectoriesPanelsList[0]
                firstPane.rightComponent = showDirectoriesPanelsList[1]
            }
            else->{
                for( i in 0.. showDirectoriesPanelsList.lastIndex-2){
                    currentPane.leftComponent = showDirectoriesPanelsList[i]
                    val noviSplitPane = createNewJSplitPane(widthOfSingleDirectoryInJSplitPane)
                    currentPane.rightComponent = noviSplitPane
                    currentPane = noviSplitPane
                }
                currentPane.leftComponent = showDirectoriesPanelsList[showDirectoriesPanelsList.lastIndex-1]
                currentPane.rightComponent = showDirectoriesPanelsList[showDirectoriesPanelsList.lastIndex]
            }
        }


        panelForJSplitPanes.add(firstPane,c)
      //  contentPane.add(firstPane, c)
    }
    fun search(wayOfSearching: WayOfSearching){
        showDirectoriesPanelsList.forEach {
            it.search(wayOfSearching)
        }
    }
    fun search(forSearch:String){

        showDirectoriesPanelsList.forEach {
            it.pretraga(forSearch)
        }
    }
    fun searchRegex(forSearchRegex: Regex){
        showDirectoriesPanelsList.forEach{
            it.pretragaRegex(forSearchRegex)
        }
    }
    fun emptyString(){
        showDirectoriesPanelsList.forEach{
            it.prazanString()
        }
    }
    fun takeStringForSearch() = enterTextForSearchingPanel.takeTextForSearching()
    fun takeRegexForSearch () = enterTextForSearchingPanel.takeRegexForSearching()
}