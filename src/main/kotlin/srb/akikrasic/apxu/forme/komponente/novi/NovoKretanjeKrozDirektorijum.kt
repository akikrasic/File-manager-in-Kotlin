package srb.akikrasic.apxu.forme.komponente.novi

import srb.akikrasic.apxu.fajlsistem.komparator
import java.io.File

class NovoKretanjeKrozDirektorijum(var putanja: String = "/") {
    var trenutniFajl = File(putanja)

    fun namestitePutanjuIVratiteNjeneFajlove(novaPutanja: String, zaPretragu:String): List<File> {
        putanja = novaPutanja
        trenutniFajl = File(novaPutanja)
        return vratiteFajloveIProverite(zaPretragu)
    }


    fun vratiteSeNaPrethodniDirektorijumIVratiteMuFajlove(zaPretragu:String): List<File> {
        if (putanja != "/") {
            trenutniFajl = trenutniFajl.parentFile ?: File("/")
            putanja = trenutniFajl.name
        }
        return vratiteFajloveIProverite(zaPretragu)
    }

    fun vratiteFajloveIProverite(zaPretragu:String):List<File>{
        val lista = vratiteFajlove()
        if(zaPretragu==""){
            return lista
        }
        return lista.filter{provera(it, zaPretragu)}

    }

    fun vratiteFajlove(): List<File> {
        return trenutniFajl.listFiles().sortedWith(komparator)
    }

    fun trenutnaApsolutnaPutanja() = trenutniFajl.absolutePath

    fun pretrazite(zaPretragu: String): List<File> =
        trenutniFajl.listFiles().filter{
            provera(it, zaPretragu)
        }.sortedWith(komparator)

    private fun provera(f:File, zaPretragu:String):Boolean = f.name.uppercase().contains(zaPretragu)

}