package pt.isel

import pt.isel.sample.Point
import java.beans.Visibility
import java.io.File
import java.net.URL
import java.net.URLClassLoader
import java.util.zip.ZipEntry
import java.util.zip.ZipInputStream
import kotlin.reflect.KClass
import kotlin.reflect.KFunction
import kotlin.reflect.KParameter
import kotlin.reflect.KVisibility
import kotlin.reflect.full.*

const val poetJar = "https://repo1.maven.org/maven2/com/squareup/javapoet/1.13.0/javapoet-1.13.0.jar"
val poetFileUrl = File("C:\\Users\\migue\\Downloads\\javapoet-1.13.0.jar").toURI().toURL()
fun main() {

    listKlasses(poetFileUrl)
            .filter { it.qualifiedName != null }
            .forEach { inspectKlass(it) }
}

fun introReflect() {
    val klassPoint: KClass<Point> = Point::class
    val p = Point(5, 7)
    println(klassPoint == p::class)
    println(klassPoint.qualifiedName)
    println("###### Properties")
    klassPoint.memberProperties.forEach { println(it.name) }
    println("###### Member Functions")
    klassPoint.memberFunctions.forEach { println(it.name) }

    val classPoint: Class<Point> = klassPoint.java
    println(classPoint.kotlin == klassPoint)
    println(classPoint.name)
    println("###### Fields")
    classPoint.declaredFields.forEach { println(it.name) }
    println("###### Methods")
    classPoint.methods.forEach { println(it.name) }
}

fun inspectKlass(klass: KClass<*>) {
	println("Details of class '${klass.qualifiedName}':")
	println("- Properties:")
	klass.memberProperties.forEach { prop ->
		println("  - ${prop.name}")
	}
	inspectFunctions(klass)
}

fun inspectFunctions(klass: KClass<*>) {
	val instance = klass
			.constructors
			.filter { it.visibility == KVisibility.PUBLIC }
			.any { it.parameters.all(KParameter::isOptional) }
			.let { if (it) klass.createInstance() else null }
	println("- Functions:")
	klass.memberFunctions.forEach { func ->
		printFunctionPrefix(func)
		print(func.name)
		printFunctionParameters(func)
		println(": ${func.returnType}")
		callFunction(func, instance)
	}
}

fun callFunction(func: KFunction<*>, instance: Any?) {
	if (func.visibility != KVisibility.PUBLIC) return
	if (func.parameters.isEmpty()) {
		val res = func.call()
		println("    =======> $res")
		return
	}
 	if (func.parameters.size == 1
		&& func.instanceParameter?.kind == KParameter.Kind.INSTANCE
		&& instance != null
	) {
		val res = func.call(instance)
		println("    =======> $res")
	}
}

fun printFunctionParameters(func: KFunction<*>) {
	print("(")
	// Print this if exist
	func.instanceParameter?.let {
		if(it.kind == KParameter.Kind.INSTANCE)
		print("this, ")
	}
	// Print other parameters
	func
		.parameters
		.filter { it != func.instanceParameter }
		.map { param -> "${param.name}: ${param.type}"}
		.joinToString()
		.let { print("$it") }
	print(")")
}

fun printFunctionPrefix(func: KFunction<*>) {
	val prefix = when (func.visibility) {
		KVisibility.PRIVATE -> "private fun"
		KVisibility.INTERNAL -> "internal fun"
		KVisibility.PROTECTED -> "protected fun"
		else -> "fun"
	}
	print("  - $prefix ")
}

fun listKlasses(url: URL): List<KClass<*>> {
    return ZipInputStream(url.openStream()).use { zip ->
        URLClassLoader(arrayOf(url)).use { loader -> zip
			.iterable()
			.filter { entry -> !entry.isDirectory && entry.name.indexOf(".class") >= 0 }
			.map { loader.loadClass(qualifiedName(it)).kotlin }
        }
    }
}

fun ZipInputStream.iterable() = object : Iterable<ZipEntry> {
	override fun iterator() = object : Iterator<ZipEntry> {
		var entry = this@iterable.nextEntry
		override fun hasNext() = entry != null

		override fun next(): ZipEntry {
			val curr = entry
			entry = this@iterable.nextEntry
			return curr
		}
	}
}

fun qualifiedName(entry: ZipEntry) = entry
        .name
        .replace('/', '.') // including ".class"
        .let { name -> name.substring(0, name.length - ".class".length) }
