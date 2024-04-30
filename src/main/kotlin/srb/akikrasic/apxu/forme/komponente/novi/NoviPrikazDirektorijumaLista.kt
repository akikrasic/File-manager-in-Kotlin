package srb.akikrasic.apxu.forme.komponente.novi

import srb.akikrasic.apxu.forme.Forma2
import srb.akikrasic.apxu.forme.komponente.ListaModel
import srb.akikrasic.apxu.forme.komponente.ListaRenderer
import java.awt.*
import java.awt.event.ActionListener
import java.awt.event.MouseEvent
import java.awt.event.MouseListener
import java.io.File
import javax.swing.*

class NoviPrikazDirektorijumaLista(val forma: Forma2, var putanja:String = "/") : JPanel(){
    val labelaPrva = JLabel("Тренутни директоријум")
    val labelaDirektorijum = JLabel(putanja)
    val nazad = JButton("Назад")
    val lista = JList<File>()
    val skrolLista = JScrollPane(lista)

    val kretanjeKrozDirektorijum = NovoKretanjeKrozDirektorijum(putanja)

    init{
        layout = GridBagLayout()
        val c = GridBagConstraints()
        c.insets = Insets(5,5,5,5)
        c.gridx = 0
        c.gridy = 0
        c.fill = GridBagConstraints.BOTH
        c.weightx = 1.0




        postaviteListuFajlovaUListuZaPrikaz(kretanjeKrozDirektorijum.vratiteFajlove())
        lista.cellRenderer = ListaRenderer()
        lista.addMouseListener(object: MouseListener {

            override fun mouseClicked(e: MouseEvent?) {

                if(e?.clickCount==2) {
                    odabranJeFajlIzListe(lista.selectedValue ?: File("/"))
                    return
                }

            }

            override fun mousePressed(e: MouseEvent?) {

                if( e?.isPopupTrigger?:false){

                    val menu = JPopupMenu()
                    val item = JMenuItem("Додајте")
                    val el = lista.model.getElementAt(lista.locationToIndex(Point(e?.x?:0, e?.y?:0)))
                    menu.add(item)
                    item.addActionListener{event->
                        forma.dodajteNoviDirektorijum(el.absolutePath)
                    }
                    if( el.isDirectory) {
                        menu.show(lista, e?.x ?: 0, e?.y ?: 0)
                    }
                }
            }

            override fun mouseReleased(e: MouseEvent?) {

            }

            override fun mouseEntered(e: MouseEvent?) {
            }

            override fun mouseExited(e: MouseEvent?) {
            }

        })

        nazad.addActionListener( ActionListener { iditeNazad() })
        listOf(labelaPrva, labelaDirektorijum, nazad)
            .forEach {
                add(it, c)
                c.gridy++
            }
        c.weighty=0.8
        add(skrolLista, c)

    }

    private fun odabranJeFajlIzListe(f:File){
        if(f.isDirectory){
            ucitajteNoviDirektorijum(f)
        }
        else{
            Desktop.getDesktop().open(f)
        }
    }

    private fun postaviteListuFajlovaUListuZaPrikaz(l:List<File>){
        lista.model = ListaModelSaListom(l)
        labelaDirektorijum.text = kretanjeKrozDirektorijum.trenutnaApsolutnaPutanja()
    }
    private fun ucitajteNoviDirektorijum(f:File){
       postaviteListuFajlovaUListuZaPrikaz( kretanjeKrozDirektorijum.namestitePutanjuIVratiteNjeneFajloveRegex(f.absolutePath, forma.izvuciteRegexZaPretragu()) )
    }
    private fun iditeNazad(){
        postaviteListuFajlovaUListuZaPrikaz( kretanjeKrozDirektorijum.vratiteSeNaPrethodniDirektorijumIVratiteMuFajloveRegex(forma.izvuciteRegexZaPretragu()) )
    }
    fun pretraga(zaPretragu:String){
        postaviteListuFajlovaUListuZaPrikaz(kretanjeKrozDirektorijum.pretrazite(zaPretragu))
    }
    fun pretragaRegex( zaPretraguRegex:Regex){
        postaviteListuFajlovaUListuZaPrikaz(kretanjeKrozDirektorijum.pretraziteRegex(zaPretraguRegex))
    }
    fun prazanString(){
        postaviteListuFajlovaUListuZaPrikaz(kretanjeKrozDirektorijum.vratiteFajlove())
    }


}