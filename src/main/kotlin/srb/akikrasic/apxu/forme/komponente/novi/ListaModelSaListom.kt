package srb.akikrasic.apxu.forme.komponente.novi

import java.io.File
import javax.swing.ListModel
import javax.swing.event.ListDataListener

class ListaModelSaListom(val lista:List<File>) : ListModel<File> {
    override fun getSize(): Int  = lista.size

    override fun getElementAt(index: Int): File  = lista[index]

    override fun addListDataListener(l: ListDataListener?) {

    }

    override fun removeListDataListener(l: ListDataListener?) {
    }
}