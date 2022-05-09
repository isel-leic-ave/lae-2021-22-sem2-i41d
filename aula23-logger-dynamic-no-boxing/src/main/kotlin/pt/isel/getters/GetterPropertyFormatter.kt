package pt.isel.getters

import pt.isel.Formatter
import pt.isel.Getter
import pt.isel.Printer
import pt.isel.ToLog
import kotlin.reflect.KProperty
import kotlin.reflect.full.createInstance
import kotlin.reflect.full.findAnnotation

class GetterPropertyFormatter(val out: Printer, val prop: KProperty<*>): Getter {
    private val formatter: Formatter = prop
        .findAnnotation<ToLog>()
        ?.formatter
        ?.createInstance() as Formatter

    override fun readAndPrint(target: Any) {
        val v = prop.call(target)
        val res = formatter.format(v)
        out.print("${prop.name} = $res, ")
    }
}
