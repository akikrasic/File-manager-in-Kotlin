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
    fun namestitePutanjuIVratiteNjeneFajloveRegex(novaPutanja: String, zaPretraguRegex:Regex): List<File> {
        putanja = novaPutanja
        trenutniFajl = File(novaPutanja)
        return vratiteFajloveIProveriteRegex(zaPretraguRegex)
    }


    fun vratiteSeNaPrethodniDirektorijumIVratiteMuFajloveRegex(zaPretraguRegex:Regex): List<File> {
        if (putanja != "/") {
            trenutniFajl = trenutniFajl.parentFile ?: File("/")
            putanja = trenutniFajl.name
        }
        return vratiteFajloveIProveriteRegex(zaPretraguRegex)
    }

    fun vratiteFajloveIProverite(zaPretragu:String):List<File>{
        val lista = vratiteFajlove()
        if(zaPretragu==""){
            return lista
        }
        return lista.filter{provera(it, zaPretragu)}

    }
    fun vratiteFajloveIProveriteRegex(zaPretraguRegex:Regex):List<File>{
        val lista = vratiteFajlove()
        return lista.filter{proveraRegex(it, zaPretraguRegex)}

    }

    fun vratiteFajlove(): List<File> {
        return trenutniFajl.listFiles()?.sortedWith(komparator)?: listOf()
    }

    fun trenutnaApsolutnaPutanja() = trenutniFajl.absolutePath

    fun pretrazite(zaPretragu: String): List<File> =
        trenutniFajl.listFiles()?.filter{
            provera(it, zaPretragu)
        }?.sortedWith(komparator)?: listOf()

    private fun provera(f:File, zaPretragu:String):Boolean = f.name.uppercase().contains(zaPretragu)

    private fun proveraRegex(f:File, zaPretraguRegex: Regex) = zaPretraguRegex.containsMatchIn(f.name)

    fun pretraziteRegex(zaPretraguRegex: Regex): List<File>  =
        trenutniFajl.listFiles()?.filter {
            proveraRegex(it, zaPretraguRegex)
        }?.sortedWith(komparator)?:listOf()

}