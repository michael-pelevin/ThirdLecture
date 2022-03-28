package FourthTask

class File(name: String) : AbstractFileSystemNode(name) {
    override var parent = ""
    override var name: String = ""
    override var path = ""

    init {
        if (name.split(".").size == 2) {
            this.name = name
            path = name
            parent = ""
            changePath(name)
        } else {
            print("Wrong file name. File not created.")
        }
    }

    //вывод расширения файла
    fun extension(): String {
        var ext = ""
        ext = if (name != null) {
            name!!.split(".")[1]
        } else {
            "File not found."
        }
        return ext
    }
}