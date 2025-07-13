package srb.akikrasic.apxu.forme.komponente.forms.formforfilesearching.models

import java.io.File
import javax.swing.ListModel
import javax.swing.event.ListDataListener

class DirectoryListModel(val list:List<File>) : ListModel<File> {
    override fun getSize(): Int  = list.size

    override fun getElementAt(index: Int): File = list[index]

    override fun addListDataListener(l: ListDataListener?) {

    }

    override fun removeListDataListener(l: ListDataListener?) {
    }
}