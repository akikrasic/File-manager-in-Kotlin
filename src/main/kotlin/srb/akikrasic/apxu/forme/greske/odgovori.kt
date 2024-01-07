package srb.akikrasic.apxu.forme.greske

import srb.akikrasic.apxu.forme.jezici.JezikServis

interface Odgovor{

    fun uRedu():Boolean
    fun poruka():String
}
class UReduOdgovor:Odgovor{
    override fun uRedu():Boolean{
        return true
    }
    override fun poruka():String{
        return ""
    }
}

object PozitivanOdgovor{
    val uReduOdgovor = UReduOdgovor()
    fun uRedu():UReduOdgovor{
        return uReduOdgovor
    }
}