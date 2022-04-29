package pt.isel

import pt.isel.MembersKind.*
import pt.isel.getters.GetterFunction
import pt.isel.getters.GetterFunctionFormatter
import pt.isel.getters.GetterProperty
import pt.isel.getters.GetterPropertyFormatter
import kotlin.reflect.full.*
import kotlin.reflect.KClass

abstract class AbstractLogger(val out: Printer = PrinterConsole(), val kind: MembersKind = PROPERTIES) {

    val getters : MutableMap<KClass<*>, List<Getter>> = mutableMapOf()


    fun log(target: Any) {
        out.print(target::class.simpleName)
        out.print("(")
        loadGetters(target::class)
            .forEach { it.readAndPrint(target) }
        out.println(")")
    }

    fun loadGetters(klass: KClass<*>) : List<Getter> {
        return when(kind) {
            PROPERTIES -> loadProperties(klass)
            FUNCTIONS -> loadFunctions(klass)
        }
    }

    val unitType = Unit::class.createType()

    private fun loadFunctions(klass: KClass<*>) : List<Getter> {
        return getters.computeIfAbsent(klass, ::createGetterFunctions)
    }

    private fun loadProperties(klass: KClass<*>): List<Getter> {
        return getters.computeIfAbsent(klass, ::createGetterProperties)
    }

    abstract fun createGetterFunctions(klass: KClass<*>) : List<Getter>

    abstract fun createGetterProperties(klass: KClass<*>) : List<Getter>
}
