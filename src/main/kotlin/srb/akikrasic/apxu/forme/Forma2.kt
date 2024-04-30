package srb.akikrasic.apxu.forme

import srb.akikrasic.apxu.fajlsistem.PocetnePutanje
import srb.akikrasic.apxu.forme.jezici.JezikServis
import srb.akikrasic.apxu.forme.jezici.PromenaJezika
import srb.akikrasic.apxu.forme.komponente.novi.NoviPanelZaPretragu
import srb.akikrasic.apxu.forme.komponente.novi.NoviPrikazDirektorijumaLista
import java.awt.GridBagConstraints
import java.awt.GridBagLayout
import java.awt.GridBagLayoutInfo
import java.awt.JobAttributes.DialogType
import java.io.File
import javax.swing.*
import javax.swing.filechooser.FileFilter
import javax.swing.filechooser.FileView

class Forma2: FormaZajednicka(), PromenaJezika {

    val noviPanelZaPretragu = NoviPanelZaPretragu(this)
    val noviPrikaziDirektorijumaLista = mutableListOf<NoviPrikazDirektorijumaLista>()
    val panelZaSplitPaneove = JPanel()
    var sirina = 0
    override fun ucitajteJezikUPanel(mapa: Map<String, String>) {
        title=mapa["naslov"]
    }

    override fun vratiteMapu(): Map<String, String>  = JezikServis.odabraniJezik().forma2()


    init{

        noviPrikaziDirektorijumaLista.addAll(PocetnePutanje.vratitePocetnePutanje().map { NoviPrikazDirektorijumaLista(this, it) })
        //ajde pisem sve opet cisto da se podsetim iako vec imam taj kod skoro sve
        this.defaultCloseOperation= JFrame.EXIT_ON_CLOSE
        val velicinaEkrana = toolkit.screenSize.size
        val visina = velicinaEkrana.height
         sirina = velicinaEkrana.width

        setSize(sirina, visina)
        isVisible = true
        contentPane.layout= GridBagLayout()
        val c = GridBagConstraints()
        c.gridx=0
        c.gridy=0
        c.weightx=0.2
        c.weighty=0.2
        c.fill = GridBagConstraints.FIRST_LINE_START
        contentPane.add(noviPanelZaPretragu)
        c.weighty=0.8
        c.gridy=1
        c.fill = GridBagConstraints.BOTH
        //c.weightx= 1.0/noviPrikaziDirektorijumaLista.size
        c.weightx = 1.0
//        noviPrikaziDirektorijumaLista.forEach{
//            contentPane.add(it, c)
//            c.gridx++
//        }
        contentPane.add(panelZaSplitPaneove, c)
        panelZaSplitPaneove.layout = GridBagLayout()
        postaviteContentPane(sirina)
        dodajteMeni()
    }

    private fun dodajteMeni() {
        val bar: JMenuBar = JMenuBar()
        val meni = JMenu("Мени")
        bar.add(meni)
        val dugmeDodajteNoviDirektorijumZaPretragu = JMenuItem("Додајте")
        meni.add(dugmeDodajteNoviDirektorijumZaPretragu)
        dugmeDodajteNoviDirektorijumZaPretragu.addActionListener { e ->
            val dialog = JFileChooser()

            dialog.fileSelectionMode = JFileChooser.DIRECTORIES_ONLY
            val result = dialog.showOpenDialog(null)
            if( result==JFileChooser.APPROVE_OPTION){
               // println(dialog.selectedFile)
               dodajteNoviDirektorijum(dialog.selectedFile.absolutePath)
            }

        }

        jMenuBar = bar
    }
    fun dodajteNoviDirektorijum(putanjaDoNovogDirektorijuma:String){

        val novi  = NoviPrikazDirektorijumaLista(this@Forma2, putanjaDoNovogDirektorijuma )
        noviPrikaziDirektorijumaLista.add(novi)
        //novi.pretraga(noviPanelZaPretragu.izvuciteStringZaPretragu())
        novi.pretragaRegex(noviPanelZaPretragu.izvuciteRegexZaPretragu())
        panelZaSplitPaneove.removeAll()
        postaviteContentPane(sirina)
        panelZaSplitPaneove.repaint()
        panelZaSplitPaneove.revalidate()
    }

    private fun  napraviteJSplitPane(sirinaPanea:Int):JSplitPane{
        val pane = JSplitPane()
        pane.isOneTouchExpandable = true
        pane.dividerLocation= sirinaPanea

        return pane
    }

    fun postaviteContentPane( sirina:Int){
        val brojDirektorijuma = noviPrikaziDirektorijumaLista.size
        val sirinaPanea = sirina/brojDirektorijuma
        val firstPane = napraviteJSplitPane(sirinaPanea)
        var currentPane = firstPane

        val c = GridBagConstraints()
        c.weightx=1.0
        c.weighty=1.0
        c.fill = GridBagConstraints.BOTH
        c.gridx=0
        c.gridy = 0

        when(brojDirektorijuma){
             1->{
                 panelZaSplitPaneove.add(noviPrikaziDirektorijumaLista[0], c)
                 return
             }
            2->{
                firstPane.leftComponent = noviPrikaziDirektorijumaLista[0]
                firstPane.rightComponent = noviPrikaziDirektorijumaLista[1]
            }
            else->{
                for( i in 0.. noviPrikaziDirektorijumaLista.lastIndex-2){
                    currentPane.leftComponent = noviPrikaziDirektorijumaLista[i]
                    val noviSplitPane = napraviteJSplitPane(sirinaPanea)
                    currentPane.rightComponent = noviSplitPane
                    currentPane = noviSplitPane
                }
                currentPane.leftComponent = noviPrikaziDirektorijumaLista[noviPrikaziDirektorijumaLista.lastIndex-1]
                currentPane.rightComponent = noviPrikaziDirektorijumaLista[noviPrikaziDirektorijumaLista.lastIndex]
            }
        }


        panelZaSplitPaneove.add(firstPane,c)
      //  contentPane.add(firstPane, c)
    }
    fun pretraga(zaPretragu:String){

        noviPrikaziDirektorijumaLista.forEach {
            it.pretraga(zaPretragu)
        }
    }
    fun pretragaRegex(zaPretraguRegex: Regex){
        noviPrikaziDirektorijumaLista.forEach{
            it.pretragaRegex(zaPretraguRegex)
        }
    }
    fun prazanString(){
        noviPrikaziDirektorijumaLista.forEach{
            it.prazanString()
        }
    }
    fun izvuciteStringZaPretragu() = noviPanelZaPretragu.izvuciteStringZaPretragu()
    fun izvuciteRegexZaPretragu () = noviPanelZaPretragu.izvuciteRegexZaPretragu()
}