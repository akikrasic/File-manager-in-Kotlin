package srb.akikrasic.apxu.forme.greske

import srb.akikrasic.apxu.forme.jezici.JezikServis
import srb.akikrasic.apxu.forme.jezici.PromenaJezika

abstract  class Greska:Odgovor, PromenaJezika{
    protected var porukaText:String =""
    override fun uRedu(): Boolean =false
    init{
        ucitajteJezik()
    }

    override fun poruka():String =porukaText
    abstract fun stringZaPoruku():String

     override fun ucitajteJezikUPanel(mapa: HashMap<String, String>) {
         porukaText=mapa[stringZaPoruku()]!!;

     }

    override fun vratiteMapu(): HashMap<String, String> = JezikServis.odabraniJezik().greske()

}
class NijeUReduPostojiSaIstimImenom:Greska(){
    override fun stringZaPoruku(): String= "nijeUReduPostojiSaIstimImenom"


}
class NijeOdabranaArhiva:Greska(), PromenaJezika{
    override fun stringZaPoruku(): String = "nijeOdabranaArhiva"

}
object Greske :PromenaJezika{
    val nijeUReduPostojiSaIstimImenom =NijeUReduPostojiSaIstimImenom()
    val nijeOdabranaArhiva = NijeOdabranaArhiva()
    override fun ucitajteJezikUPanel(mapa: HashMap<String, String>) {

    }

    override fun vratiteMapu(): HashMap<String, String> {
        return HashMap<String, String>()
    }

    override fun promeniteJezik() {
        nijeUReduPostojiSaIstimImenom.promeniteJezik()
        nijeOdabranaArhiva.promeniteJezik()
    }

}

