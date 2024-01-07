package srb.akikrasic.apxu.forme.komponente.novi

import srb.akikrasic.apxu.forme.Forma2
import srb.akikrasic.apxu.forme.komponente.ListaModel
import srb.akikrasic.apxu.forme.komponente.ListaRenderer
import java.awt.Desktop
import java.awt.GridBagConstraints
import java.awt.GridBagLayout
import java.awt.Insets
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
                }
            }

            override fun mousePressed(e: MouseEvent?) {
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
       postaviteListuFajlovaUListuZaPrikaz( kretanjeKrozDirektorijum.namestitePutanjuIVratiteNjeneFajlove(f.absolutePath, forma.izvuciteStringZaPretragu()) )
    }
    private fun iditeNazad(){
        postaviteListuFajlovaUListuZaPrikaz( kretanjeKrozDirektorijum.vratiteSeNaPrethodniDirektorijumIVratiteMuFajlove(forma.izvuciteStringZaPretragu()) )
    }

    fun pretraga(zaPretragu:String){
        postaviteListuFajlovaUListuZaPrikaz(kretanjeKrozDirektorijum.pretrazite(zaPretragu))
    }
    fun prazanString(){
        postaviteListuFajlovaUListuZaPrikaz(kretanjeKrozDirektorijum.vratiteFajlove())
    }


}