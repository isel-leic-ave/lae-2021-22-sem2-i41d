package pt.isel

import pt.isel.MembersKind.*
import kotlin.reflect.full.*
import kotlin.reflect.KClass

class Logger(val out: Printer = PrinterConsole(), val kind: MembersKind = PROPERTIES) {

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

    private fun createGetterFunctions(klass: KClass<*>) : List<Getter> {
        return klass
            .declaredMembers
            .filter { it.returnType != unitType && it.valueParameters.isEmpty() }
            .filter { it.hasAnnotation<ToLog>()}
            .map { prop -> object : Getter {
                override fun readAndPrint(target: Any) {
                    val v = prop.call(target)
                    out.print("${prop.name}() = $v, ") 
                }
            }}

    }

    private fun createGetterProperties(klass: KClass<*>) : List<Getter> {
        return klass
            .memberProperties
            .filter { it.hasAnnotation<ToLog>()}
            .map { prop ->
                val formatter = prop.findAnnotation<ToLog>()?.formatter
                object : Getter {
                    override fun readAndPrint(target: Any) {
                        val v = prop.call(target)
                        val res = formatter?.let {
                            val f = it.createInstance() as Formatter
                            f.format(v)
                        } ?: v
                        out.print("${prop.name} = $res, ")
                    }
                }
            }
    }
}
