package srb.akikrasic.apxu.fajlsistem

import java.io.File
import javax.swing.filechooser.FileSystemView

object PocetnePutanje {
    val fajlMoj = "/home/aki/arhuPocetnePutanje"
    //neka za pocetak bude ovako
    val pocetnePutanjeZaSve = napravitePocetnePutanje()

    val majmunskiWindows = System.getProperty("os.name").uppercase().contains("WINDOWS")

    val daLiTrebaDaSeSnimePutanje = true

    fun napravitePocetnePutanje():String{
        val pocetnePutanje = "pocetnePutanje"
        val dir = FileSystemView.getFileSystemView().homeDirectory.absolutePath
        if( dir[dir.lastIndex]=='/' || dir[dir.lastIndex]=='\\'){
            return "${dir}${pocetnePutanje}"
        }
        if(majmunskiWindows){
            return "${dir}\\${pocetnePutanje}"
        }
        return "${dir}/${pocetnePutanje}"
    }

    fun ucitajtePocetnePutanje():List<String> {
        val pocetnePutanjeZaSveFajl = File(pocetnePutanjeZaSve)
        if( pocetnePutanjeZaSveFajl.exists()){
            return pocetnePutanjeZaSveFajl.readLines()
        }
        val mojePocetnePutanje = File(fajlMoj)
        if( mojePocetnePutanje.exists()) {
            return mojePocetnePutanje.readLines()
        }
        if( majmunskiWindows) {
            return listOf("C:\\");
        }
        return listOf(FileSystemView.getFileSystemView().homeDirectory.absolutePath)

    }

    fun sacuvajtePutanjeZaSledeciPut(lista:List<String> ){
        if( daLiTrebaDaSeSnimePutanje) {
            val pocetnePutanjeZaSveFajl = File(pocetnePutanjeZaSve)
            val upisivac = pocetnePutanjeZaSveFajl.writer()
            lista.forEach {
                upisivac.write("${it}\n")
            }
            upisivac.flush()
        }
    }

}