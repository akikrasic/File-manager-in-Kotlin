package srb.akikrasic.apxu.forme.komponente

import srb.akikrasic.apxu.forme.jezici.Jezik
import srb.akikrasic.apxu.forme.jezici.JezikServis
import srb.akikrasic.apxu.forme.jezici.PromenaJezika
import java.awt.Rectangle
import javax.swing.JComponent
import javax.swing.JPanel

 open class PanelZajednicki: JPanel(), PromenaJezika{
   /*  fun ucitajteJezik(){
        val jezik = JezikServis.odabraniJezik()
        ucitajteJezikUPanel(vratiteMapuZaUcitavanjeJezika(jezik))
    }*/
   protected open  fun rasporedjivanje(){

   }
    fun rasporedjivanjeJednoIspodDrugog(listaObjekata:List<JComponent>){
        val x = 10
        var y = 0
        val sirina = 200
        val visina = 30
        for (komponenta in listaObjekata){
            rasporediteKomponentu(komponenta,x,y,sirina, visina)
            y+=40
        }
    }
    init{
        layout = null;
    }
    fun rasporediteKomponentu(komponenta: JComponent, x:Int, y:Int, sirina:Int, visina:Int){
        komponenta.bounds= Rectangle(x,y,sirina, visina)
        add(komponenta)
    }

     open override fun ucitajteJezikUPanel(mapa: Map<String, String>) {

     }

     override fun vratiteMapu(): Map<String, String> {
         return HashMap<String, String>()
     }

 }