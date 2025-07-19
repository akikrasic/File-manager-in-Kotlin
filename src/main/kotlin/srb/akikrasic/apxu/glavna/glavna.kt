package srb.akikrasic.apxu.glavna

import com.formdev.flatlaf.FlatLightLaf
import srb.akikrasic.apxu.fajlsistem.PocetnePutanje
import srb.akikrasic.apxu.forme.komponente.forms.formforfilesearching.FormForFileSearchingInMultipleDirectories
import srb.akikrasic.apxu.podaci.Podaci
import javax.swing.SwingUtilities
import javax.swing.UIManager

fun main(args: Array<String>){


    println(Podaci.vratiteListuArhiva())
    println(PocetnePutanje.ucitajtePocetnePutanje())
    System.setProperty("awt.useSystemAAFontSettings","on");
    System.setProperty("swing.aatext", "true");
    SwingUtilities.invokeLater{

        UIManager.setLookAndFeel(FlatLightLaf())

        val f = FormForFileSearchingInMultipleDirectories()
      //  JezikServis.forma = f
      //  JezikServis.promeniteJezik()
    }





}