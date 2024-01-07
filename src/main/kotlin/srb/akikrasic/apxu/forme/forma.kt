package srb.akikrasic.apxu.forme


import srb.akikrasic.apxu.forme.jezici.Jezik
import srb.akikrasic.apxu.forme.jezici.JezikServis
import srb.akikrasic.apxu.forme.jezici.PromenaJezika
import srb.akikrasic.apxu.forme.komponente.*
import srb.akikrasic.apxu.forme.komponente.*
import srb.akikrasic.apxu.podaci.Podaci
import java.awt.Rectangle
import java.awt.event.ActionEvent
import java.awt.event.ActionListener
import java.awt.event.KeyEvent
import java.io.File
import javax.swing.*
class FormaSaJezikom


class Forma : JFrame(), PromenaJezika {
     val prikazDirektorijuma= PrikazDirektorijumaLista(this)
     val panel: PanelZajednicki = PanelZajednicki()
     val meni: Meni = Meni(this)
     var sirinaEkrana:Int = 0
     var visinaEkrana :Int = 0
     val panelArhiveRad: PanelArhiveRad = PanelArhiveRad(this)
     val panelArhiveSve = PanelArhiveSve(this)
     val panelPrikazFajla = PanelPrikazFajla(this)

    override fun ucitajteJezikUPanel(mapa: Map<String, String>) {
        title=mapa["naslov"]
        meni.meniJezik(mapa)
    }

    override fun vratiteMapu(): HashMap<String, String>  = JezikServis.odabraniJezik().forma()


    override fun promeniteJezik(){
        super.promeniteJezik()
        prikazDirektorijuma.promeniteJezik()
        panelArhiveRad.promeniteJezik()
        panelArhiveSve.promeniteJezik()
        panelPrikazFajla.promeniteJezik()
    }
    private fun velicinaEkrana(){
        val dimenzijeEkrana = toolkit.screenSize
        sirinaEkrana = dimenzijeEkrana.width
        visinaEkrana = dimenzijeEkrana.height
    }

    private fun pocetak() {

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE)
        velicinaEkrana()
        setSize(sirinaEkrana,  visinaEkrana)
        setVisible(true)
        contentPane = panel
        jMenuBar = meni.meniPostavljanje()
        ucitajteJezik()

    }
    private fun rasporediteKomponentu(komponenta:JComponent, x:Int, y:Int, sirina:Int, visina:Int ){
        panel.rasporediteKomponentu(komponenta,x,y,sirina, visina)
    }
    private fun rasporedjivanje(){
        val sp :JSplitPane = JSplitPane()
        sp.leftComponent = prikazDirektorijuma
        val desniPanel = PanelZajednicki()
        desniPanel.rasporediteKomponentu(panelArhiveRad,0,0, 320, 360)
        desniPanel.rasporediteKomponentu(panelPrikazFajla,0,370,320,300)
        desniPanel.rasporediteKomponentu(panelArhiveSve,330,0,300,600)
        sp.rightComponent = desniPanel
        sp.orientation = JSplitPane.HORIZONTAL_SPLIT
        sp.dividerLocation = 320
        rasporediteKomponentu(sp,0,0,sirinaEkrana,visinaEkrana)
        //rasporediteKomponentu(prikazDirektorijuma,0,0,500,700)


    }


    private fun dodajteKomanduTastature(fja:()->Unit,ks:KeyStroke,kljuc:String ){
        val akcija = object:AbstractAction(){
            override fun actionPerformed(e: ActionEvent?) {
                fja()
            }

        };
        this.rootPane.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(ks, kljuc)
        this.rootPane.actionMap.put(kljuc, akcija)
    }
    private fun ctrl(slovo:Int):KeyStroke{

        return KeyStroke.getKeyStroke(slovo,KeyEvent.CTRL_DOWN_MASK)
    }
    private  val kljucCtrlF:String = "ctrlF"
    fun fokusCtrlF(){

        val fja:()->Unit ={
            prikazDirektorijuma.fokusirajte();

        }
        val ks = ctrl(KeyEvent.VK_F)
        dodajteKomanduTastature(fja,ks, kljucCtrlF)
    }
    init {
        ucitajteJezik()
        pocetak()
        rasporedjivanje()
        fokusCtrlF()
    }
    fun osveziteSveArhive(){
        panelArhiveSve.osvezite()
    }

}
