package srb.akikrasic.apxu.forme.komponente

import java.io.File
import javax.swing.ListModel
import javax.swing.event.ListDataListener

class ListaModel(val lista:Array<File>): ListModel<File> {
    override fun getElementAt(index: Int): File {
        return lista[index]
    }

    override fun getSize(): Int {
        return lista.size
    }

    override fun addListDataListener(l: ListDataListener?) {
    }

    override fun removeListDataListener(l: ListDataListener?) {
    }

}