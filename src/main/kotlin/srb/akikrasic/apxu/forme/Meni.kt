package srb.akikrasic.apxu.forme

import srb.akikrasic.apxu.forme.jezici.JezikServis
import javax.swing.JMenu
import javax.swing.JMenuBar
import javax.swing.JMenuItem

class Meni(val forma: Forma){
    private val meniBar = JMenuBar()
    private val meniJezik = JMenu()
    private val meniSrbski = JMenuItem()
    private val meniEngleski  = JMenuItem()
    fun meniJezik(mapa:Map<String, String> ){
        meniJezik.text = mapa["meniJezik"]
        meniSrbski.text = mapa["meniSrbski"]
        meniEngleski.text = mapa["meniEngleski"]
    }

    fun meniPostavljanje(): JMenuBar {
        meniJezik.add(meniSrbski)
        meniJezik.add(meniEngleski)
        meniBar.add(meniJezik)
        meniSrbski.addActionListener{
            JezikServis.ucitajteSrbski();
        }

        meniEngleski.addActionListener {
            JezikServis.ucitajteEngleski()

        }


        return meniBar
    }
}