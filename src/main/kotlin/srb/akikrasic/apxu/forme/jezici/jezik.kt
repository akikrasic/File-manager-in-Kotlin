package srb.akikrasic.apxu.forme.jezici

import srb.akikrasic.apxu.forme.Forma
import srb.akikrasic.apxu.forme.greske.Greske
import srb.akikrasic.apxu.forme.komponente.PrikazDirektorijumaLista
import srb.akikrasic.apxu.podaci.Podaci

abstract class Jezik{
    protected fun mapa():HashMap<String, String>{
        return HashMap<String, String>()
    }
    protected val formaMapa:HashMap<String,String> = mapa()
    protected val prikazDirektorijumaListaMapa:HashMap<String, String> = mapa()
    protected val panelDodajteArhivuMapa:HashMap<String, String> =mapa()
    protected val panelArhiveRadMapa :HashMap<String, String> = mapa()
    protected val panelArhiveSveMapa :HashMap<String, String> = mapa()
    protected val panelIzmeniteArhivuMapa =mapa()
    protected val panelPrikazFajla =mapa()
    protected val podaciMapa = mapa()
    protected val nijeUReduPostojiSaIstimImenomMapa = mapa()
    protected val greskeMapa = mapa()
     fun forma():HashMap<String, String>{
         return formaMapa
     }
     fun prikazDirektorijumaLista():HashMap<String, String>{
         return prikazDirektorijumaListaMapa
     }
    fun panelDodajteArhivu():HashMap<String, String>{
        return panelDodajteArhivuMapa
    }
    fun panelArhiveRad():HashMap<String,String>{
        return panelArhiveRadMapa
    }
    fun panelArhiveSve():HashMap<String, String>{
        return panelArhiveSveMapa
    }
    fun  panelIzmeniteArhivu():HashMap<String, String>{
        return panelIzmeniteArhivuMapa
    }
    fun panelPrikazFajla():HashMap<String, String>{
        return panelPrikazFajla;
    }
    fun podaci():HashMap<String, String>{
        return podaciMapa
    }
    fun greske():HashMap<String, String>{
        return greskeMapa
    }

}
interface PromenaJezika{
    fun ucitajteJezikUPanel(mapa:HashMap<String, String>)
    fun vratiteMapu():HashMap<String, String>
    fun ucitajteJezik(){
        ucitajteJezikUPanel(vratiteMapu())
    }
    fun promeniteJezik(){
        ucitajteJezik()
    }
}

object JezikServis{

    private val engleski = Engleski()
    private val ???????????? = ????????????();
    private var trenutniJezik: Jezik = ????????????
    lateinit var forma:Forma
    fun promeniteJezik(){
        forma.promeniteJezik()
        Greske.promeniteJezik()
    }
    fun ucitajteSrbski(){
        trenutniJezik = ????????????
        promeniteJezik()
    }
    fun ucitajteEngleski(){
        trenutniJezik = engleski
        promeniteJezik()
    }
    fun odabraniJezik(): Jezik {
        return trenutniJezik
    }

}
