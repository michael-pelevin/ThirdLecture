package FourthTask

abstract class AbstractFileSystemNode(name: String) : FileSystemNode {
    open var parent = ""
    open var name = ""
    open var path = ""

    init {
        this.name = name
    }

    fun changePath(newPath: String) {
        path = newPath
    }

    fun changeParent(newParent: String) {
        parent = newParent
    }

    override fun returnParent(): String {
        return parent
    }

    override fun returnName(): String {
        return name
    }

    override fun returnPath(): String {
        return path
    }
}