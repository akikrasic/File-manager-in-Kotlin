package srb.akikrasic.apxu.forme.komponente

import srb.akikrasic.apxu.fajlsistem.KretanjeKrozFajlSistem
import srb.akikrasic.apxu.forme.Forma
import srb.akikrasic.apxu.forme.jezici.Jezik
import srb.akikrasic.apxu.forme.jezici.JezikServis
import java.awt.Component
import java.awt.Desktop
import java.awt.Rectangle
import java.awt.event.KeyEvent
import java.awt.event.KeyListener
import java.awt.event.MouseEvent
import java.awt.event.MouseListener
import java.io.File
import java.util.*
import javax.swing.*
import javax.swing.event.ListDataListener
import javax.swing.filechooser.FileSystemView
import kotlin.collections.HashMap



class PrikazDirektorijumaLista(var forma: Forma, val putanja:String="/"): PanelZajednicki() {

    val kretanjeKrozFajlSistem = KretanjeKrozFajlSistem()
    val labelaDirektorijumNatpis:JLabel =  JLabel("");
    val poljeTrenutniDirektorijum = JTextField(kretanjeKrozFajlSistem.putanja)
    val labelaPretragaNatpis:JLabel= JLabel("")
    val unosTextaZaPretragu = JTextField()
    val lista = JList<File>()
    val dugmeNazad :JButton = JButton()
    private val listaSkrol = JScrollPane(lista)


    override  fun rasporedjivanje(){
        rasporedjivanjeIspod()
    }
    private fun rasporedjivanjePored(){
        rasporediteKomponentu(labelaDirektorijumNatpis,0,0,200, 30)
        rasporediteKomponentu(poljeTrenutniDirektorijum,210,0,200,30)
        rasporediteKomponentu(labelaPretragaNatpis,0,50,200,30)
        rasporediteKomponentu(unosTextaZaPretragu,210,50,200,30)
        rasporediteKomponentu(dugmeNazad,420,50,80,30)
        rasporediteKomponentu(listaSkrol,0,90,500,500)
    }

    private fun rasporedjivanjeIspod(){
        val listaKomponenti: List<JComponent> = listOf(labelaDirektorijumNatpis,poljeTrenutniDirektorijum,labelaPretragaNatpis, unosTextaZaPretragu, dugmeNazad)
        rasporedjivanjeJednoIspodDrugog(listaKomponenti)
        /*rasporediteKomponentu(labelaDirektorijumNatpis,10,0,200, 30)
        rasporediteKomponentu(poljeTrenutniDirektorijum,10,40,200,30)
        rasporediteKomponentu(labelaPretragaNatpis,10,80,200,30)
        rasporediteKomponentu(unosTextaZaPretragu,10,120,200,30)
        rasporediteKomponentu(dugmeNazad,10,160,80,30)
        */rasporediteKomponentu(listaSkrol,10,200,300,500)
    }

    private fun pocistitePretragu(){
        unosTextaZaPretragu.text=""
    }
    fun namestiteListu(){

        lista. model = ListaModel(kretanjeKrozFajlSistem.srediteListuZaNamestanje())
    }
    private fun ucitajteDirektorijumUListu(f:File){
        sreditePutanju(f)
        namestiteListu()
        pocistitePretragu()
    }

    private fun sreditePutanju( f:File){

        poljeTrenutniDirektorijum.text=kretanjeKrozFajlSistem.sreditePutanjuPrePostavljanja(f)
    }
    private fun pretraga(  traziSe:String){

        lista.model = ListaModel(kretanjeKrozFajlSistem.pretraga(traziSe))
    }

    override fun vratiteMapu(): HashMap<String, String> {
        return JezikServis.odabraniJezik().prikazDirektorijumaLista()
    }
    override fun ucitajteJezikUPanel(mapa:Map<String, String> ) {
        labelaDirektorijumNatpis.text = mapa["labelaDirektorijumNatpis"]
        labelaPretragaNatpis.text = mapa["labelaPretragaNatpis"]
        dugmeNazad.text =mapa["dugmeNazad"]
    }

    public fun fokusirajte(){
        unosTextaZaPretragu.requestFocus()
        unosTextaZaPretragu.selectAll()
    }

    private fun namestiteOsluskivace(){
        lista.addMouseListener(ListaKlik())
        lista.cellRenderer = ListaRenderer()
        dugmeNazad.addActionListener{
            ucitajteDirektorijumUListu(kretanjeKrozFajlSistem.prethodni)
        }
        unosTextaZaPretragu.addKeyListener(PretragaPosleSvakogDugmetaNaTastaturi())
        poljeTrenutniDirektorijum.addKeyListener(PretragaNaEnter())
    }
    init{

        ucitajteJezik()
        rasporedjivanje()
        namestiteListu()
        namestiteOsluskivace()

    }
    inner class ListaKlik :MouseListener{
        override fun mouseReleased(e: MouseEvent?) {
        }

        override fun mouseEntered(e: MouseEvent?) {
        }

        override fun mouseClicked(e: MouseEvent?) {
            val odabrani = lista.selectedIndex
            val f:File =lista.model.getElementAt(odabrani)?:File("/")
            if(f.isDirectory){
                ucitajteDirektorijumUListu(f)
            }
            else{
                //val p = Runtime.getRuntime().exec("xdg-open "+f.absolutePath)!!
                forma.panelPrikazFajla.prikaziteFajl(f)
                //Desktop.getDesktop().open(f)
                //forma.stampajZmijutu(f)
                /*val inp = f.inputStream()
                val bajtovi =inp.readAllBytes()// inp.readNBytes(120)
                val s= String(bajtovi)
                println(s)*/
            }
        }

        override fun mouseExited(e: MouseEvent?) {
        }

        override fun mousePressed(e: MouseEvent?) {
        }

    }
 inner class PretragaPosleSvakogDugmetaNaTastaturi:KeyListener{
        override fun keyTyped(e: KeyEvent?) {

        }

        override fun keyPressed(e: KeyEvent?) {

        }

        override fun keyReleased(e: KeyEvent?) {
            pretraga(unosTextaZaPretragu.text)
        }

    }
 inner class PretragaNaEnter:KeyListener{
        override fun keyTyped(e: KeyEvent?) {

        }

        override fun keyPressed(e: KeyEvent?) {

        }

        override fun keyReleased(e: KeyEvent?) {

            val id :Int = e!!.keyCode;
            if(id==10){//enter je 10
                val s:String = poljeTrenutniDirektorijum.getText()?:""
                val f = File(s)
                if(f.isDirectory){
                    kretanjeKrozFajlSistem.putanja = s;
                    namestiteListu()
                }
            }
        }

    }
}