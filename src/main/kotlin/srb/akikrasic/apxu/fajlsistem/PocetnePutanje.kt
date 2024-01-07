package srb.akikrasic.apxu.fajlsistem

import java.io.File

object PocetnePutanje {

    fun ucitajteIzFajlaAkoIma(){
        val os = System.getProperty("os.name")

    }
    fun vratitePocetnePutanje():List<String> =File("/home/aki/arhuPocetnePutanje").readLines()

}