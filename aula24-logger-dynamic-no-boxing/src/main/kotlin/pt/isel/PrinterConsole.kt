package pt.isel

class PrinterConsole : Printer {
    override fun print(msg: Any?) {
        kotlin.io.print(msg)
    }

    override fun print(msg: Int) {
        kotlin.io.print(msg)
    }

    override fun print(msg: Long) {
        kotlin.io.print(msg)
    }

    override fun print(msg: Short) {
        kotlin.io.print(msg)
    }

    override fun print(msg: Float) {
        kotlin.io.print(msg)
    }

    override fun print(msg: Byte) {
        kotlin.io.print(msg)
    }
}
