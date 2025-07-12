package srb.akikrasic.apxu.podaci

import srb.akikrasic.apxu.forme.greske.Greske
import srb.akikrasic.apxu.forme.greske.Odgovor
import srb.akikrasic.apxu.forme.greske.PozitivanOdgovor
import srb.akikrasic.apxu.forme.jezici.JezikServis
import srb.akikrasic.apxu.forme.jezici.PromenaJezika
import srb.akikrasic.apxu.podaci.pojedinacni.Arhiva

object Podaci{
    val listaArhiva = mutableListOf<Arhiva>()
    val praznaArhiva = Arhiva("","")
    val skupImenaArhiva = mutableSetOf<String>()

    private fun ubacivanjeArhiveUSistem(naziv:String, opis:String){
        val a :Arhiva = Arhiva(naziv, opis)
        skupImenaArhiva.add(naziv)
        listaArhiva.add(a)
    }
    private fun izbacivanjeArhiveIzSistema(a:Arhiva){
        listaArhiva.remove(a)
        skupImenaArhiva.remove(a.ime)
    }
    fun dodajteArhivu( naziv:String, opis:String): Odgovor {
        if(naziv in skupImenaArhiva){
            return Greske.nijeUReduPostojiSaIstimImenom
        }
        ubacivanjeArhiveUSistem(naziv, opis)
        return PozitivanOdgovor.uRedu()
    }
    fun izmeniteArhivu(noviNaziv:String, noviOpis:String,odabranaArhiva:Arhiva ):Odgovor{
        if(noviNaziv==odabranaArhiva.ime){
            izbacivanjeArhiveIzSistema(odabranaArhiva)
            ubacivanjeArhiveUSistem(noviNaziv, noviOpis)
            return PozitivanOdgovor.uRedu()
        }
        if(noviNaziv in skupImenaArhiva){
           return Greske.nijeUReduPostojiSaIstimImenom
       }
        listaArhiva.remove(odabranaArhiva)
        skupImenaArhiva.remove(odabranaArhiva.ime)
        ubacivanjeArhiveUSistem(noviNaziv,noviOpis)

        return PozitivanOdgovor.uRedu()
    }
    fun vratiteListuArhiva ()= listaArhiva



}