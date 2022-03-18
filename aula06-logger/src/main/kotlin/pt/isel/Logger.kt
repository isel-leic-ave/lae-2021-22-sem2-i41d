package pt.isel

import pt.isel.MembersKind.*
import kotlin.reflect.full.memberProperties

class Logger(val out: Printer = PrinterConsole(), val kind: MembersKind = PROPERTIES) {

    fun log(target: Any) {
        when(kind) {
            PROPERTIES -> logProperties(target)
            FUNCTIONS -> logFunctions(target)
        }
    }

    private fun logFunctions(target: Any) {


    }

    fun logProperties(target: Any) {
        out.print(target::class.simpleName)
        target::class
                .memberProperties
                .map { "${it.name} = ${it.call(target)}" }
                .joinToString()
                .let { out.println("($it)") }

    }
}
