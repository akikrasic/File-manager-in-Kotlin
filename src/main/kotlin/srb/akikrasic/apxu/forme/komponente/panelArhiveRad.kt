package srb.akikrasic.apxu.forme.komponente

import srb.akikrasic.apxu.forme.Forma
import srb.akikrasic.apxu.forme.greske.Greske
import srb.akikrasic.apxu.forme.greske.Odgovor
import srb.akikrasic.apxu.forme.jezici.JezikServis
import srb.akikrasic.apxu.podaci.Podaci
import srb.akikrasic.apxu.podaci.pojedinacni.Arhiva
import javax.swing.*

open class PanelDodajteArhivu(protected open val rad:PanelArhiveRad): PanelZajednicki(){
    val labelaNaziv = JLabel()
    val poljeUnosNaziva = JTextField()
    val labelaOpis = JLabel()
    val poljeUnosOpisa = JTextArea()
    val dugmeDodajteArhivu = JButton()
    var porukaPrazanNaziv:String=""
    var porukaNaslov :String = ""

     override  fun ucitajteJezikUPanel(mapa:HashMap<String, String>) {
         labelaNaziv.text = mapa["labelaNaziv"]
         labelaOpis.text = mapa["labelaOpis"]
         dugmeDodajteArhivu.text = mapa["dugmeDodajteArhivu"]
         porukaPrazanNaziv = mapa["porukaPrazanNaziv"]!!
         porukaNaslov = mapa["porukaNaslov"]!!

    }

    override fun vratiteMapu(): HashMap<String, String> {
        return JezikServis.odabraniJezik().panelDodajteArhivu()
    }

    override fun rasporedjivanje(){
        val listaKomponenti:List<JComponent> = listOf(labelaNaziv,poljeUnosNaziva, labelaOpis)
        rasporedjivanjeJednoIspodDrugog(listaKomponenti)
        rasporediteKomponentu(JScrollPane(poljeUnosOpisa),10,120,300 ,150)
        rasporediteKomponentu(dugmeDodajteArhivu,10,280,200,30)
    }
    fun osluskivac(){
        dugmeDodajteArhivu.addActionListener{
            osluskivanje()
        }
    }
    fun fokusirajte(){
        poljeUnosNaziva.requestFocus()
        poljeUnosNaziva.selectAll()
    }
    fun pocistite(){
        poljeUnosNaziva.text=""
        poljeUnosOpisa.text=""
        fokusirajte()
    }
    init {
        ucitajteJezik()
        rasporedjivanje()
        osluskivac()
    }
    private fun dijalog(poruka:String){
        JOptionPane.showMessageDialog(rad.forma,poruka, porukaNaslov,JOptionPane.INFORMATION_MESSAGE)
        fokusirajte()
    }
    open fun kadTrebaDaSeDoda(naziv:String): Odgovor {
        return Podaci.dodajteArhivu(naziv, poljeUnosOpisa.text.trim())
    }
     fun osluskivanje(){
        val naziv = poljeUnosNaziva.text.trim()
        if(naziv!=""){
            val odg= kadTrebaDaSeDoda(naziv)
            if(odg.uRedu()) {
                rad.forma.osveziteSveArhive()
                pocistite()
                rad.forma.panelArhiveSve.deselektujte()
                rad.ponistiteArhivu()
                rad.panelIzmeniteArhivu.pocistite()
            }
            else{
                dijalog(odg.poruka())
            }

        }
        else{
            dijalog(porukaPrazanNaziv)

        }



    }

}

class PanelIzmeniteArhivu( override val rad:PanelArhiveRad): PanelDodajteArhivu(rad){

    override fun vratiteMapu(): HashMap<String, String> {
        return JezikServis.odabraniJezik().panelIzmeniteArhivu()
    }
    /*
    override fun ucitajteJezikUPanel(mapa:HashMap<String, String>) {
        super.ucitajteJezikUPanel(mapa)
        labelaNaziv.text = mapa["labelaNaziv"]
        labelaOpis.text = mapa["labelaOpis"]
        dugmeDodajteArhivu.text = mapa["dugmeDodajteArhivu"]
    }*/
    init{
        ucitajteJezik()

    }
    fun promenjenaJeArhiva(a:Arhiva){
        poljeUnosNaziva.text=a.ime
        poljeUnosOpisa.text=a.opis
        fokusirajte()
    }

    override fun kadTrebaDaSeDoda(naziv: String): Odgovor {
        if(rad.odabranaArhiva==Podaci.praznaArhiva){
            return Greske.nijeOdabranaArhiva
        }
        val odgovor= Podaci.izmeniteArhivu(naziv, poljeUnosOpisa.text.trim(),rad.odabranaArhiva)
        return odgovor
    }
}
class PanelObrisiteArhivu(val rad:PanelArhiveRad): PanelZajednicki(){
    fun promenjenaJeArhiva(a:Arhiva){

    }
}

class PanelArhiveRad(val forma: Forma): PanelZajednicki(){
    val tabovi = JTabbedPane()
    val ii = ImageIcon()// za sada neka bude prazna
    val panelDodajteArhivu = PanelDodajteArhivu(this)
    val panelIzmeniteArhivu = PanelIzmeniteArhivu(this)
    val panelObrisiteArhivu = PanelObrisiteArhivu(this)
    var odabranaArhiva:Arhiva = Podaci.praznaArhiva
    override fun vratiteMapu(): HashMap<String, String> {
        return  JezikServis.odabraniJezik().panelArhiveRad()
    }
    override fun ucitajteJezikUPanel(mapa:HashMap<String, String> ) {
        tabovi.setTitleAt(0,mapa["dodajte"])
        tabovi.setTitleAt(1,mapa["izmenite"])
        tabovi.setTitleAt(2,mapa["obrisite"])
        tabovi.setToolTipTextAt(0,mapa["dodajte"])
        tabovi.setToolTipTextAt(1,mapa["izmenite"])
        tabovi.setToolTipTextAt(2,mapa["obrisite"])

    }
    override fun rasporedjivanje(){
        rasporediteKomponentu(tabovi,0,0,320,360)
    }
    override fun promeniteJezik(){
        super.promeniteJezik()
        panelDodajteArhivu.promeniteJezik()
        panelIzmeniteArhivu.promeniteJezik()
        panelObrisiteArhivu.promeniteJezik()
    }
 init {

     tabovi.addTab("", ii, panelDodajteArhivu,"")
     tabovi.addTab("", ii, panelIzmeniteArhivu,"")
     tabovi.addTab("",ii, panelObrisiteArhivu,"")
     ucitajteJezik()
     rasporedjivanje()

 }
    fun postaviteOdabranuArhivu(a:Arhiva){
        odabranaArhiva = a
        panelIzmeniteArhivu.promenjenaJeArhiva(odabranaArhiva)
        panelObrisiteArhivu.promenjenaJeArhiva(odabranaArhiva)
    }
    fun ponistiteArhivu(){
        odabranaArhiva = Podaci.praznaArhiva
    }
}


