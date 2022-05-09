package pt.isel.getters

import pt.isel.Formatter
import pt.isel.Getter
import pt.isel.Printer
import pt.isel.ToLog
import kotlin.reflect.KCallable
import kotlin.reflect.full.createInstance
import kotlin.reflect.full.findAnnotation

class GetterFunctionFormatter(val out: Printer, val func: KCallable<*>) : Getter {
    private val formatter: Formatter = func
            .findAnnotation<ToLog>()
            ?.formatter
            ?.createInstance() as Formatter

    override fun readAndPrint(target: Any) {
        val v = func.call(target)
        val res = formatter.format(v)
        out.print("${func.name}() = $res, ")
    }
}
