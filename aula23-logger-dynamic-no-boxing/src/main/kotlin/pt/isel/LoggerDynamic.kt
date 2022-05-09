package pt.isel

import com.squareup.javapoet.JavaFile
import pt.isel.MembersKind.*
import pt.isel.getters.*
import kotlin.reflect.KCallable
import kotlin.reflect.full.*
import kotlin.reflect.KClass
import kotlin.reflect.KProperty
import kotlin.reflect.KProperty1

class LoggerDynamic(out: Printer = PrinterConsole(), kind: MembersKind = PROPERTIES) :
    AbstractLogger(out, kind)
{

    override fun createGetterFunctions(klass: KClass<*>) : List<Getter> {
        return klass
            .declaredMembers
            .filter { it.returnType != unitType && it.valueParameters.isEmpty() }
            .filter { it.hasAnnotation<ToLog>()}
            .map { func ->
                val file: JavaFile = buildGetterFunctionDynamic(klass, func)
                loadAndCreateInstance(file, out) as Getter
            }
    }

    override fun createGetterProperties(klass: KClass<*>) : List<Getter> {
        return klass
            .memberProperties
            .filter { it.hasAnnotation<ToLog>()}
            .map { prop ->
                val file: JavaFile = buildGetterPropertyDynamic(klass, prop)
                loadAndCreateInstance(file, out) as Getter
            }
    }
}
