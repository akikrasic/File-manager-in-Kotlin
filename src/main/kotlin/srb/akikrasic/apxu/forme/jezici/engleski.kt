package srb.akikrasic.apxu.forme.jezici
class Engleski: Jezik(){

    init{
        formaMapa["naslov"]="Apxu archive"
        formaMapa["meniJezik"] = "Language"
        formaMapa["meniSrbski"]= "Serbian"
        formaMapa["meniEngleski"] = "English"
        prikazDirektorijumaListaMapa["labelaDirektorijumNatpis"] ="Current directory"
        prikazDirektorijumaListaMapa["labelaPretragaNatpis"]= "Search"
        prikazDirektorijumaListaMapa["dugmeNazad"] = "Back"


        panelDodajteArhivuMapa["labelaNaziv"]="Name"
        panelDodajteArhivuMapa["labelaOpis"]="Description"
        panelDodajteArhivuMapa["dugmeDodajteArhivu"]="Add archive"
        panelDodajteArhivuMapa["porukaPrazanNaziv"]="You must provide the name of archive"
        panelDodajteArhivuMapa["porukaNaslov"]="Message"


        panelIzmeniteArhivuMapa["labelaNaziv"]="Name"
        panelIzmeniteArhivuMapa["labelaOpis"]="Description"
        panelIzmeniteArhivuMapa["dugmeDodajteArhivu"]="Update archive"
        panelIzmeniteArhivuMapa["porukaPrazanNaziv"]="You must provide the name of archive"
        panelIzmeniteArhivuMapa["porukaNaslov"]="Message"

        panelArhiveRadMapa["dodajte"]= "Add archive"
        panelArhiveRadMapa["izmenite"] = "Update archive"
        panelArhiveRadMapa["obrisite"] = "Delete archive"

        panelArhiveSveMapa["labelaNaslov"]="All archives"


        panelPrikazFajla["labelaNazivFajlaNatpis"] = "Name"

        greskeMapa["nijeUReduPostojiSaIstimImenom"]="Archive with that name already exist in system."
        greskeMapa["nijeOdabranaArhiva"] ="Select archive"
    }


}
