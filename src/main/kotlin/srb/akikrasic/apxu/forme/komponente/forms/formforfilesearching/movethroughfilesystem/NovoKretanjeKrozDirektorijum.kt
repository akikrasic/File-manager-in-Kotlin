package srb.akikrasic.apxu.forme.komponente.forms.formforfilesearching.movethroughfilesystem

import srb.akikrasic.apxu.fajlsistem.komparator
import srb.akikrasic.apxu.forme.komponente.forms.formforfilesearching.movethroughfilesystem.wayofsearching.WayOfSearching
import srb.akikrasic.apxu.forme.komponente.forms.formforfilesearching.movethroughfilesystem.wayofsearching.WholeStringWayOfSearching
import java.io.File

class NovoKretanjeKrozDirektorijum(var currentDirectoryString: String = "/") {
    var currentDirectoryFile  = File(currentDirectoryString)
    var currentWayOfSearching: WayOfSearching = WholeStringWayOfSearching("")
    fun setCurrentDirecoryStringAndFile(currentDirectory:String){
        currentDirectoryString = currentDirectory
        currentDirectoryFile = File(currentDirectory)
    }
    fun namestitePutanjuIVratiteNjeneFajlove(novaPutanja: String, zaPretragu:String): List<File> {
        currentDirectoryString = novaPutanja
        currentDirectoryFile = File(novaPutanja)
        return vratiteFajloveIProverite(zaPretragu)
    }
    fun namestitePutanjuIVratiteNjeneFajloveRegex(novaPutanja: String, zaPretraguRegex:Regex): List<File> {
        currentDirectoryString = novaPutanja
        currentDirectoryFile = File(novaPutanja)
        return vratiteFajloveIProveriteRegex(zaPretraguRegex)
    }


    fun vratiteSeNaPrethodniDirektorijumIVratiteMuFajlove(zaPretragu:String): List<File> {
        if (currentDirectoryString != "/") {
            currentDirectoryFile = currentDirectoryFile.parentFile ?: File("/")
            currentDirectoryString = currentDirectoryFile.name
        }
        return vratiteFajloveIProverite(zaPretragu)
    }


    fun vratiteSeNaPrethodniDirektorijumIVratiteMuFajloveRegex(zaPretraguRegex:Regex): List<File> {
        if (currentDirectoryString != "/") {
            currentDirectoryFile = currentDirectoryFile.parentFile ?: File("/")
            currentDirectoryString = currentDirectoryFile.name
        }
        return search(this.currentWayOfSearching)
    }

    fun vratiteFajloveIProverite(zaPretragu:String):List<File>{
        val lista = getAllFilesFromCurrentDirectorySorted()
        if(zaPretragu==""){
            return lista
        }
        return lista.filter{provera(it, zaPretragu)}

    }
    fun vratiteFajloveIProveriteRegex(zaPretraguRegex:Regex):List<File>{
        val lista = getAllFilesFromCurrentDirectorySorted()//to mi ne valja!!!
        return lista.filter{proveraRegex(it, zaPretraguRegex)}

    }

    fun getAllFilesFromCurrentDirectorySorted(): List<File> {
        return currentDirectoryFile.listFiles()?.sortedWith(komparator)?: listOf()
    }

    fun trenutnaApsolutnaPutanja() = currentDirectoryFile.absolutePath

    fun search(wayOfSearching: WayOfSearching):List<File> {
        currentWayOfSearching = wayOfSearching
        return if (!wayOfSearching.shouldNotSearch()) {
            currentDirectoryFile.listFiles()?.filter {
                wayOfSearching.fileNameMatches(it)
            }?.sortedWith(komparator) ?: listOf()
        } else {
            getAllFilesFromCurrentDirectorySorted()
        }
    }


    fun pretrazite(zaPretragu: String): List<File> =
        currentDirectoryFile.listFiles()?.filter{
            provera(it, zaPretragu)
        }?.sortedWith(komparator)?: listOf()
    fun pretraziteRegex(zaPretraguRegex: Regex): List<File>  =
        currentDirectoryFile.listFiles()?.filter {
            proveraRegex(it, zaPretraguRegex)
        }?.sortedWith(komparator)?:listOf()


    private fun provera(f: File, zaPretragu:String):Boolean = f.name.uppercase().contains(zaPretragu)

    private fun proveraRegex(f: File, zaPretraguRegex: Regex) = zaPretraguRegex.containsMatchIn(f.name)



}