package srb.akikrasic.apxu.forme

import srb.akikrasic.apxu.fajlsistem.PocetnePutanje
import srb.akikrasic.apxu.forme.jezici.JezikServis
import srb.akikrasic.apxu.forme.jezici.PromenaJezika
import srb.akikrasic.apxu.forme.komponente.novi.NoviPanelZaPretragu
import srb.akikrasic.apxu.forme.komponente.novi.NoviPrikazDirektorijumaLista
import java.awt.GridBagConstraints
import java.awt.GridBagLayout
import javax.swing.JFrame
import javax.swing.JSplitPane

class Forma2: FormaZajednicka(), PromenaJezika {

    val noviPanelZaPretragu = NoviPanelZaPretragu(this)
    val noviPrikaziDirektorijumaLista = PocetnePutanje.vratitePocetnePutanje().map { NoviPrikazDirektorijumaLista(this, it) }
    override fun ucitajteJezikUPanel(mapa: Map<String, String>) {
        title=mapa["naslov"]
    }

    override fun vratiteMapu(): Map<String, String>  = JezikServis.odabraniJezik().forma2()

    private fun  napraviteJSplitPane(sirinaPanea:Int):JSplitPane{
        val pane = JSplitPane()
        pane.isOneTouchExpandable = true
        pane.dividerLocation= sirinaPanea

        return pane
    }


    init{
        //ajde pisem sve opet cisto da se podsetim iako vec imam taj kod skoro sve
        this.defaultCloseOperation= JFrame.EXIT_ON_CLOSE
        val velicinaEkrana = toolkit.screenSize.size
        val visina = velicinaEkrana.height
        val sirina = velicinaEkrana.width

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

        postaviteContentPane(c, sirina)

    }



    fun postaviteContentPane(c:GridBagConstraints, sirina:Int){
        val brojDirektorijuma = noviPrikaziDirektorijumaLista.size
        val sirinaPanea = sirina/brojDirektorijuma
        val firstPane = napraviteJSplitPane(sirinaPanea)
        var currentPane = firstPane

        when(brojDirektorijuma){
             1->{
                 contentPane.add(noviPrikaziDirektorijumaLista[0], c)
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


        contentPane.add(firstPane, c)
    }
    fun pretraga(zaPretragu:String){

        noviPrikaziDirektorijumaLista.forEach {
            it.pretraga(zaPretragu)
        }
    }
    fun prazanString(){
        noviPrikaziDirektorijumaLista.forEach{
            it.prazanString()
        }
    }
    fun izvuciteStringZaPretragu() = noviPanelZaPretragu.izvuciteStringZaPretragu()

}