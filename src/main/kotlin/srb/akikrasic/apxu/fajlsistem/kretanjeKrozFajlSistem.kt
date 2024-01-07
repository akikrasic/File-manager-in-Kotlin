package srb.akikrasic.apxu.fajlsistem

import java.awt.Desktop
import java.io.File
import java.util.*

val komparator = Komparator()
class KretanjeKrozFajlSistem(var putanja:String="/"){

    lateinit var  prethodni:File
    var fajloviUPutanji:Array<File>
    init {
        fajloviUPutanji = izracunajteFajloveUPutanji(File(putanja))
    }
    private fun izracunajteFajloveUPutanji(f:File):Array<File>{
        return f.listFiles()?:arrayOf()
    }
    fun srediteListuZaNamestanje():Array<File>{
        val f  = File(putanja)
        prethodni = f.parentFile?: File("/")
        fajloviUPutanji  = izracunajteFajloveUPutanji(f)

        Arrays.sort(fajloviUPutanji,komparator )
        return fajloviUPutanji
    }

     fun sreditePutanjuPrePostavljanja(f:File):String{
         putanja = f.absolutePath;
         prethodni = f.parentFile?:File("/")
         return putanja
    }

     fun pretraga1(traziSe:String):Array<File>{
        val zaPretragu = traziSe.uppercase()
        val listaPronadjenihFajlova:MutableList<File> = mutableListOf()
        for(f in fajloviUPutanji){
            if(f.name.uppercase().contains(zaPretragu)){
                listaPronadjenihFajlova.add(f)
            }
        }
        val niz = listaPronadjenihFajlova.toTypedArray()
        Arrays.sort(niz, komparator)
        return niz
    }

    fun pretraga(traziSe:String):Array<File>{
        val zaPretragu = traziSe.uppercase()
         return fajloviUPutanji
             .filter {
                it.name.uppercase().contains(zaPretragu)
             }
             .sortedWith(komparator)
             .toTypedArray()
    }

}