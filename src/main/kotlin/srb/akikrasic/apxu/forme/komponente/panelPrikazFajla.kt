package srb.akikrasic.apxu.forme.komponente

import srb.akikrasic.apxu.forme.Forma
import srb.akikrasic.apxu.forme.jezici.Jezik
import srb.akikrasic.apxu.forme.jezici.JezikServis
import java.awt.Desktop
import java.io.File
import javax.swing.JButton
import javax.swing.JComponent
import javax.swing.JLabel

class PanelPrikazFajla(val forma: Forma): PanelZajednicki(){
    val labelaNazivFajlaNatpis = JLabel()
    val labelaNazivFajla = JLabel()
    val labelaOdabranaArhivaNatpis = JLabel()
    val dugmeDodajteUArhivu = JButton()
    override fun vratiteMapu(): HashMap<String, String> {
        return JezikServis.odabraniJezik().panelPrikazFajla()
    }
    override fun ucitajteJezikUPanel(mapa:HashMap<String, String>) {

        labelaNazivFajlaNatpis.text=mapa["labelaNazivFajlaNatpis"]
    }
    fun ucitajteFajl(f: File){

    }
    override fun rasporedjivanje(){
        val lista:List<JComponent> = listOf(labelaNazivFajlaNatpis, labelaNazivFajla, labelaOdabranaArhivaNatpis, dugmeDodajteUArhivu);
        this.rasporedjivanjeJednoIspodDrugog(lista)
    }
    init{
        ucitajteJezik()
        rasporedjivanje()
        dugmeUcitavanje()
    }
    fun dugmeUcitavanje(){
        dugmeDodajteUArhivu.text="Упалите фајл"
        dugmeDodajteUArhivu.addActionListener {
            val f = forma.prikazDirektorijuma.lista.selectedValue
            Desktop.getDesktop().open(f)
        }
    }
    fun prikaziteFajl(f:File){
        labelaNazivFajla.text=f.name
    }
}