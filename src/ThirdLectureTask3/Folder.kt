package FourthTask

class Folder : AbstractFileSystemNode {
    override var parent = ""
    override var name = ""
    override var path = ""
    var children = ArrayList<Any>()

    constructor(name: String) : super(name) {
        this.name = name
        parent = ""
        path = name
        changePath(name)
    }

    constructor(name: String, vararg v: Any) : super(name) {
        this.name = name
        path = name
        parent = ""
        changePath(name)
        for (i in v.indices) {
            val child = v[i]
            if (child is File) {
                child.path = this.name + "/" + child.path
                child.changePath(child.path)
                child.parent = this.name
                child.changeParent(this.name)
            }
            if (child is Folder) {
                child.parent = this.name
                child.changeParent(name)
                child.path = child.parent + "/" + child.path
                child.changePath(child.path)
                if (child.children.size != 0) {
                    changePaths(child.children, child.path)
                }
            }
            children.add(child)
        }
    }

    //изменение потей дочених файлов и папок
    private fun changePaths(v: ArrayList<*>, parent: String) {
        for (i in v.indices) {
            val child = v[i]
            if (child is File) {
                child.path = parent + "/" + child.name
                child.changePath(child.path)
            }
            if (child is Folder) {
                child.path = parent + "/" + child.name
                child.changePath(child.path)
                if (child.children.size != 0) {
                    changePaths(child.children, child.path)
                }
            }
        }
    }

    //способ добавления дочерних элементов после созданиия папки
    fun addChildren(vararg v: Any) {
        for (i in v.indices) {
            val child = v[i]
            if (child is File) {
                child.path = name + "/" + child.name
                child.changePath(child.path)
                child.parent = name
                child.changeParent(name)
            }
            if (child is Folder) {
                child.path = name + "/" + child.path
                child.changePath(child.path)
                child.parent = name
                child.changeParent(name)
                if (child.children.size != 0) {
                    changePaths(child.children, child.path)
                }
            }
            children.add(child)
        }
    }

    //вывод списка дочерних элементов
    fun listChildren(): ArrayList<*> {
        return children
    }

    //вывод в виде строки всех дочерних элементов текущей папки
    fun showChildren(): String {
        var currentChildren = ""
        if (children.size != 0) {
            for (child in children) {
                if (child is File) {
                    currentChildren += """
                        ${child.name}
                        
                        """.trimIndent()
                }
                if (child is Folder) {
                    currentChildren += """
                        ${child.name}
                        
                        """.trimIndent()
                }
            }
        } else {
            currentChildren += "Children list empty"
        }
        return currentChildren
    } //имя текущей папки
    //    public String returnName(){
    //        return this.name;
    //    }
}