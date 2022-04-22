package pt.isel

class PrinterConsole : Printer {
    override fun print(msg: Any?) {
        kotlin.io.print(msg)
    }
}
