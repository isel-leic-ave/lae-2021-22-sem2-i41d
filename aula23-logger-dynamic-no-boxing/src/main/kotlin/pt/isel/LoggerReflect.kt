package pt.isel

import pt.isel.MembersKind.*
import pt.isel.getters.GetterFunction
import pt.isel.getters.GetterFunctionFormatter
import pt.isel.getters.GetterProperty
import pt.isel.getters.GetterPropertyFormatter
import kotlin.reflect.full.*
import kotlin.reflect.KClass

class LoggerReflect(out: Printer = PrinterConsole(), kind: MembersKind = PROPERTIES) :
    AbstractLogger(out, kind)
{

    override fun createGetterFunctions(klass: KClass<*>) : List<Getter> {
        return klass
            .declaredMembers
            .filter { it.returnType != unitType && it.valueParameters.isEmpty() }
            .filter { it.hasAnnotation<ToLog>()}
            .map { func -> if(func.findAnnotation<ToLog>()?.formatter != EmptyFormatter::class)
                GetterFunctionFormatter(out, func)
                else GetterFunction(out, func)
            }
    }
    override fun createGetterProperties(klass: KClass<*>) : List<Getter> {
        return klass
            .memberProperties
            .filter { it.hasAnnotation<ToLog>()}
            .map { prop -> if(prop.findAnnotation<ToLog>()?.formatter != EmptyFormatter::class)
                GetterPropertyFormatter(out, prop)
                else GetterProperty(out, prop)
            }
    }
}
