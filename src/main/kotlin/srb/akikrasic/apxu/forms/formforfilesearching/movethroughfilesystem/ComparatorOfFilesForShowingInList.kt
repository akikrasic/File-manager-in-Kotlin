package srb.akikrasic.apxu.forms.formforfilesearching.movethroughfilesystem

import java.io.File

class ComparatorOfFilesForShowingInList: Comparator<File> {
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