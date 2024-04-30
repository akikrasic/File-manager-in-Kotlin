package srb.akikrasic.apxu.forme

import java.awt.Dimension
import java.awt.GridBagConstraints
import java.awt.GridBagLayout
import java.awt.Rectangle
import javax.swing.JButton
import javax.swing.JDialog
import javax.swing.JFrame
import javax.swing.JSplitPane

class Forma3 : JFrame() {
    val splitPane = JSplitPane()

    init{
        isVisible= true
        defaultCloseOperation = JFrame.EXIT_ON_CLOSE
        val d = toolkit.screenSize
        size = d

        contentPane.layout = GridBagLayout()
        val c = GridBagConstraints()
        c.gridx = 0
        c.gridy = 0
        c.weightx = 1.0
        c.weighty = 1.0
        c.fill = GridBagConstraints.BOTH
        splitPane.dividerLocation = 200
        splitPane.isOneTouchExpandable = true


        add(splitPane, c)

        val dialog = JDialog(this, "Kompire")
        dialog.contentPane.add( JButton("Kompir"))
        dialog.isVisible = true

        dialog.bounds = Rectangle(toolkit.screenSize.width/2-200, toolkit.screenSize.height/2-150, 400,300)


    }

}