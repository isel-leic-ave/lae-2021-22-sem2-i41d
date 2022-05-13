package pt.isel

interface Printer {

    fun print(msg: Any?)
    fun print(msg: Int)
    fun print(msg: Long)
    fun print(msg: Short)
    fun print(msg: Float)
    fun print(msg: Byte)

    fun println(msg: Any?) {
        print(msg)
        print(System.lineSeparator())
    }
}
