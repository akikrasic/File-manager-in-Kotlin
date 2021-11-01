package srb.akikrasic.apxu.forme.komponente

import srb.akikrasic.apxu.forme.Forma
import srb.akikrasic.apxu.forme.jezici.Jezik
import srb.akikrasic.apxu.forme.jezici.JezikServis
import srb.akikrasic.apxu.podaci.Podaci
import srb.akikrasic.apxu.podaci.pojedinacni.Arhiva
import javax.swing.JLabel
import javax.swing.JList
import javax.swing.ListModel
import javax.swing.event.ListDataListener

class ModelListaArhiva(val lista:List<Arhiva>) :ListModel<Arhiva>{
    override fun getSize(): Int =
        lista.size


    override fun getElementAt(index: Int): Arhiva = lista[index]

    override fun addListDataListener(l: ListDataListener?) {

    }

    override fun removeListDataListener(l: ListDataListener?) {

    }


}
class PanelArhiveSve(val forma: Forma): PanelZajednicki(){
    val labelaNaslov  = JLabel()
    var listaSvihArhiva = JList<Arhiva>()
    override fun rasporedjivanje() {
        rasporediteKomponentu(labelaNaslov,0,0,200,30)
        rasporediteKomponentu(listaSvihArhiva,0,40,200,600)
    }

    override fun vratiteMapu(): HashMap<String, String> = JezikServis.odabraniJezik().panelArhiveSve()
    override fun ucitajteJezikUPanel(mapa:HashMap<String, String>) {
            labelaNaslov.text=mapa["labelaNaslov"]
    }
    private fun dogadjaji(){
        listaSvihArhiva.addListSelectionListener{
            if(it.valueIsAdjusting) {
               forma.panelArhiveRad.postaviteOdabranuArhivu(listaSvihArhiva.selectedValue!!)
            }
        }
    }
    init{
        ucitajteJezik()
        rasporedjivanje()
        osvezite()
        dogadjaji()
    }
    fun osvezite(){
        listaSvihArhiva.model=ModelListaArhiva(Podaci.vratiteListuArhiva())
    }
    fun deselektujte(){
        listaSvihArhiva.clearSelection()
    }

}