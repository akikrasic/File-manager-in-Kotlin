package srb.akikrasic.apxu.glavna

import com.formdev.flatlaf.FlatLightLaf
import srb.akikrasic.apxu.forme.Forma
import srb.akikrasic.apxu.forme.Forma2
import srb.akikrasic.apxu.forme.Forma3
import srb.akikrasic.apxu.forme.jezici.JezikServis
import srb.akikrasic.apxu.podaci.Podaci
import java.awt.Desktop
import java.awt.Dimension
import java.io.File
import java.nio.file.Path
import java.nio.file.Paths
import javax.swing.JFrame
import javax.swing.SwingUtilities
import javax.swing.UIManager

fun main(args: Array<String>){


    println(Podaci.vratiteListuArhiva())
    System.setProperty("awt.useSystemAAFontSettings","on");
    System.setProperty("swing.aatext", "true");
    SwingUtilities.invokeLater{

        UIManager.setLookAndFeel(FlatLightLaf())

        val f = Forma2()
      //  JezikServis.forma = f
      //  JezikServis.promeniteJezik()
    }





}