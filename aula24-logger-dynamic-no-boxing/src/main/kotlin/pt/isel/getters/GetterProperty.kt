package pt.isel.getters

import pt.isel.Getter
import pt.isel.Printer
import kotlin.reflect.KProperty

class GetterProperty(val out: Printer, val prop: KProperty<*>): Getter {
    override fun readAndPrint(target: Any) {
        val v = prop.call(target)
        out.print("${prop.name} = $v, ")
    }
}
