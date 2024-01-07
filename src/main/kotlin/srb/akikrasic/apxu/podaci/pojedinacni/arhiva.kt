package srb.akikrasic.apxu.podaci.pojedinacni

data class Arhiva(val ime:String, val opis:String){
override fun toString():String{
    return ime
}
}