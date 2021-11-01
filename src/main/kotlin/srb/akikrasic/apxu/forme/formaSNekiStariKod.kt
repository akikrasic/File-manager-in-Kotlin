package srb.akikrasic.apxu.forme



/*
val f = File("/home/aki/Downloads/ustase.jpg");
        val ik = FileSystemView.getFileSystemView().getSystemIcon(f)
        val labela = JLabel()
        labela.icon = ik
        labela.setBounds(0,0,100,100)
        contentPane.add(labela)
 */
/*
class ListaModel(var lista:Array<File>) :ListModel<String>{
    fun daLiJeDirektorijum (indeks:Int):Boolean{
        return lista[indeks].isDirectory
    }
    override fun getElementAt(index: Int): String {
        return lista[index].name
    }

    override fun getSize(): Int {
        return lista.size
    }

    override fun addListDataListener(l: ListDataListener?) {
    }

    override fun removeListDataListener(l: ListDataListener?) {
    }
    fun predjiteUDirektorijum(indeks:Int){
        if(lista[indeks].isDirectory){
            lista = lista[indeks].listFiles()
        }
    }
}

class Forma : JFrame() {
    /*
    var trenutniDirektorijum = "/ змијата"
    fun ucitajtePutanjuUListu(lista:JList<String>, putanja:String){
        val putanjaFajl = File(putanja)
        val listaFajlovaZaUcitavanje = putanjaFajl.listFiles()?: emptyArray<File>()

        lista.model = ListaModel(listaFajlovaZaUcitavanje)
    }*/
    private fun pocetak() {
        println(toolkit.screenSize)
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE)
        val dimenzijeEkrana = toolkit.screenSize
        setSize(dimenzijeEkrana.width,  dimenzijeEkrana.height)
        setVisible(true)
        contentPane.layout = null
    }

    init {
        /*
        pocetak()
        val bar = JMenuBar()
        val menu = JMenu("File")
        val item =  JMenuItem("Klikni")
        item.addActionListener{
            println("${it} zmijata ")
        }
        menu.add(item)
        bar.add( menu)


        this.jMenuBar = bar
        val node = DefaultMutableTreeNode("Zmija")
        val node1 = DefaultMutableTreeNode("Zmaj")
        node.add(node1)
        val node2 = DefaultMutableTreeNode("Zmijata")
        node1.add(node2)
        val model = DefaultTreeModel(node)


        val stablo = JTree(model)

        val panel = JPanel()
        panel.add(stablo)
        val drugiPanel = JPanel()

        val drugiSplit = JSplitPane(JSplitPane.HORIZONTAL_SPLIT,JTree(), JTree())
        drugiSplit.isOneTouchExpandable = true
        drugiSplit.dividerLocation = 150
        drugiPanel.add(drugiSplit)
        val split = JSplitPane(JSplitPane.HORIZONTAL_SPLIT, stablo,drugiPanel )
        split.isOneTouchExpandable = true
        split.dividerLocation = 150
        contentPane = split;


        pocetak()

        val trenutniDirektorijumLabela = JLabel()
        trenutniDirektorijumLabela.bounds = Rectangle(0,0,100,100)

        val lista =JList<String>()
        lista.bounds= Rectangle(0,130,500,800)
        ucitajtePutanjuUListu(lista, "/")
        val popup = JPopupMenu()
        val klik = JMenuItem("Кликни")
        klik.addActionListener{
            println("клик")
        }
        popup.add(klik)

        class Klik :MouseListener{
            override fun mouseReleased(e: MouseEvent?) {

            }

            override fun mouseEntered(e: MouseEvent?) {

            }

            override fun mouseClicked(e: MouseEvent?) {


            }

            override fun mouseExited(e: MouseEvent?) {

            }

            override fun mousePressed(e: MouseEvent?) {
                if(e?.clickCount==2){
                    println(lista.selectedValue)
                    ucitajtePutanjuUListu(lista, "/"+lista.selectedValue)
                }

            }

        }
        val klikObj = Klik()
        lista.addMouseListener(klikObj)
        contentPane.add(trenutniDirektorijumLabela)
        contentPane.add(lista)
        val icon = ImageIcon("/usr/share/icons/oxygen/64x64/places/folder-blue.png")
        trenutniDirektorijumLabela.icon=icon
        println(trenutniDirektorijumLabela.background)
        trenutniDirektorijumLabela.text = trenutniDirektorijum
        trenutniDirektorijumLabela.addMouseListener(ML())
        println(icon)
    }


}
class ML : MouseAdapter() {
    override fun mouseReleased(e: MouseEvent?) {

    }

    override fun mouseEntered(e: MouseEvent?) {
        val el = e!!.source as JLabel
        el.grabFocus()
        el.requestFocus()
println(el)
    }
*/

        pocetak()
        contentPane.layout = null
        contentPane.background = Color.WHITE
        val mali = JPanel()
        mali.layout = null
        val slika = ImageIcon("/usr/share/icons/oxygen/48x48/places/folder-blue.png")
        val labelaSlika = JLabel()
        labelaSlika.icon = slika
        val labelaTekst = JTextPane()
        labelaTekst.text = "Змијата да видимо ради ли"
        labelaSlika.background = Color.WHITE
        labelaTekst.background = Color.WHITE


        labelaTekst.alignmentX = JTextArea.CENTER_ALIGNMENT
        labelaTekst.alignmentY = JTextArea.CENTER_ALIGNMENT

        class MA : MouseAdapter(){
            override fun mouseReleased(e: MouseEvent?) {
                labelaTekst.select(0,0)
            }

            override fun mousePressed(e: MouseEvent?) {
                labelaTekst.grabFocus()
                labelaTekst.selectAll()
            }

        }



        labelaTekst.addMouseListener(MA());

        mali.background = Color.WHITE
        labelaSlika.bounds = Rectangle(0,0,50,50)
        labelaTekst.bounds = Rectangle(0,50,50,100)
        mali.add(labelaSlika)
        mali.add(labelaTekst)
        mali.bounds = Rectangle(20,0,150,150)
        contentPane.add(mali)
    }
}
*/