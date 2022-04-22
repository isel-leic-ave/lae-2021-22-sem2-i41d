package pt.isel

import kotlin.reflect.KClass

@Target(AnnotationTarget.PROPERTY, AnnotationTarget.FUNCTION)
/**
 * @param formatter A KClass that implements Formatter
 */
annotation class ToLog(val formatter: KClass<*> = EmptyFormatter::class)

class EmptyFormatter : Formatter {
    override fun format(v: Any?): Any? {
        return v
    }
}
