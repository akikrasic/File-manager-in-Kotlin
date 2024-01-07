package srb.akikrasic.apxu.forme.jezici
class Србски : Jezik(){
    init {
        formaMapa["naslov"]="Архи проба"
        formaMapa["meniJezik"] = "Језик"
        formaMapa["meniSrbski"]= "Србски"
        formaMapa["meniEngleski"] = "Енглески"

        prikazDirektorijumaListaMapa["labelaDirektorijumNatpis"] ="Тренутни директоријум"
        prikazDirektorijumaListaMapa["labelaPretragaNatpis"]= "Претрага"
        prikazDirektorijumaListaMapa["dugmeNazad"] = "Назад"

        panelDodajteArhivuMapa["labelaNaziv"]="Назив"
        panelDodajteArhivuMapa["labelaOpis"]="Опис"
        panelDodajteArhivuMapa["dugmeDodajteArhivu"]="Додајте архиву"
        panelDodajteArhivuMapa["porukaPrazanNaziv"]="Морате унети назив архиве"
        panelDodajteArhivuMapa["porukaNaslov"]="Порука"

        panelIzmeniteArhivuMapa["labelaNaziv"]="Назив"
        panelIzmeniteArhivuMapa["labelaOpis"]="Опис"
        panelIzmeniteArhivuMapa["dugmeDodajteArhivu"]="Измените архиву"
        panelIzmeniteArhivuMapa["porukaPrazanNaziv"]="Морате унети назив архиве"
        panelIzmeniteArhivuMapa["porukaNaslov"]="Порука"

        panelArhiveRadMapa["dodajte"]= "Додајте архиву"
        panelArhiveRadMapa["izmenite"] = "Измените архиву"
        panelArhiveRadMapa["obrisite"] = "Обришите архиву"



        panelArhiveSveMapa["labelaNaslov"]="Све архиве"


        panelPrikazFajla["labelaNazivFajlaNatpis"] = "Назив"

        greskeMapa["nijeUReduPostojiSaIstimImenom"]="Архива са тим именом постоји у систему."
        greskeMapa["nijeOdabranaArhiva"] = "Одаберите архиву"

        forma2Mapa = mapOf(
            "naslov" to "Вишеструка претрага"
        )
    }


}
