package srb.akikrasic.apxu.forme.komponente

import java.awt.Component
import java.io.File
import javax.swing.JLabel
import javax.swing.JList
import javax.swing.ListCellRenderer
import javax.swing.filechooser.FileSystemView

class ListaRenderer : ListCellRenderer<File> {
    override fun getListCellRendererComponent(
        lista: JList<out File>?,
        value: File?,
        index: Int,
        isSelected: Boolean,
        cellHasFocus: Boolean
    ): Component {
        val labela = JLabel()
        labela.text = value!!.name
        labela.icon =  FileSystemView.getFileSystemView().getSystemIcon(value)
        return labela
    }

}