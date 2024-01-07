package srb.akikrasic.apxu.fajlsistem

import java.io.File
import java.util.Comparator

class Komparator: Comparator<File> {
    override fun compare(o1: File?, o2: File?): Int {

        if(o1!!.isDirectory){
            if(o2!!.isDirectory){
                return o1.name.uppercase().compareTo(o2.name.uppercase())
            }
            else{
                return -1
            }
        }
        else{
            if(o2!!.isFile){
                return o1.name.uppercase().compareTo(o2.name.uppercase())
            }
            else{
                return 1
            }
        }
    }

}