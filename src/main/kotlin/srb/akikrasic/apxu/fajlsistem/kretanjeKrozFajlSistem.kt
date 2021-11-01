package srb.akikrasic.apxu.fajlsistem

import java.awt.Desktop
import java.io.File
import java.util.*

fun pocetnaPutanja():String{
    return "/"

}
val komparator = object:Comparator<File>{
    override fun compare(o1: File?, o2: File?): Int {

        if(o1!!.isDirectory){
            if(o2!!.isDirectory){
                return o1.name.toUpperCase().compareTo(o2.name.toUpperCase())
            }
            else{
                return -1
            }
        }
        else{
            if(o2!!.isFile){
                return o1.name.toUpperCase().compareTo(o2.name.toUpperCase())
            }
            else{
                return 1
            }
        }
    }

}
class KretanjeKrozFajlSistem{
    var putanja:String=pocetnaPutanja()
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

     fun pretraga(traziSe:String):Array<File>{
        val zaPretragu = traziSe.toUpperCase()
        val listaPronadjenihFajlova:MutableList<File> = mutableListOf()
        for(f in fajloviUPutanji){
            if(f.name.toUpperCase().contains(zaPretragu)){
                listaPronadjenihFajlova.add(f)
            }
        }
        val niz = listaPronadjenihFajlova.toTypedArray()
        Arrays.sort(niz, komparator)
        return niz
    }

}