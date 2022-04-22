package pt.isel

interface Printer {

    fun print(msg: Any?)

    fun println(msg: Any?) {
        print(msg)
        print(System.lineSeparator())
    }
}
