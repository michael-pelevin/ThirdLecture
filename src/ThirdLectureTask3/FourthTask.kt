package FourthTask

fun main() {

    //создание корня файловой системы
    val root = Folder("root")

    //создание некоторых папок и файлов
    val file1: File = File("file.kt")
    val file2: File = File("file1.kt")
    val file3: File = File("file2.kt")
    val folder1 = Folder("folder1", file1, file2)
    val folder2 = Folder("folder2", folder1, file3)

    //добавление в верхние каталоги системы
    root.addChildren(folder2)


    //отображение содержимого папки
    println(folder1.showChildren())

    //список дочерних элементов
    println(folder1.listChildren())

    //расширение файла
    println(file1.extension())

    //вывод названия, пути и родителя файла/папки
    println("############")
    println(file3.returnName())
    println(folder1.returnName())
    println("############")
    println(file3.returnPath())
    println(folder1.returnPath())
    println("############")
    println(file3.returnParent())
    println(folder1.returnParent())
    println("############")
    println(file2.returnName())
    println(folder2.returnName())
    println("############")
    println(file2.returnPath())
    println(folder2.returnPath())
    println("############")
    println(file2.returnParent())
    println(folder2.returnParent())
}
