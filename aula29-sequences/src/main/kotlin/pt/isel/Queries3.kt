package pt.isel

import kotlin.reflect.KClass
import kotlin.reflect.KParameter
import kotlin.reflect.full.primaryConstructor

inline fun <reified T : Any> Map<String, String>.toObject(): T {
    val klass = T::class
    return this.toObject(klass)
}

fun <T : Any> Map<String, String>.toObject(klass:  KClass<T>): T {
    /**
     * Look for constructor KParameters
     * Fill args Map to be passed on callBy() to constructor
     */
    val initArgs: Map<KParameter, Any> = klass
        .primaryConstructor
        ?.parameters
        ?.associate {
            val stringValue = this[it.name] ?: throw Exception("Missing key for constructor argument ${it.name}")
            val parser = basicParser[it.type.classifier as KClass<*>] ?: throw Exception("Constructor argument type not supported")
            val v = parser(stringValue)
            it to v
        }
        ?: throw Exception("Missing primary constructor")
    return klass.primaryConstructor?.callBy(initArgs) ?: throw Exception("Missing primary constructor")
}

private val basicParser: Map<KClass<*>, (String) -> Any> = mapOf(
        String::class to { it },
        Byte::class to { it.toByte() },
        Short::class to { it.toShort() },
        Int::class to { it.toInt() },
        Long::class to { it.toLong() },
        Float::class to { it.toFloat() },
        Double::class to { it.toDouble() },
        Boolean::class to { it.toBoolean() }
)
