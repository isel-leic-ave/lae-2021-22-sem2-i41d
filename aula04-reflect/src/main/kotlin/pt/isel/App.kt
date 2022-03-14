package pt.isel

import pt.isel.sample.Point
import java.net.URL
import java.net.URLClassLoader
import java.util.zip.ZipEntry
import java.util.zip.ZipInputStream
import kotlin.reflect.KClass
import kotlin.reflect.full.memberFunctions
import kotlin.reflect.full.memberProperties

const val poetJar = "https://repo1.maven.org/maven2/com/squareup/javapoet/1.13.0/javapoet-1.13.0.jar"

fun main() {
    listKlasses(URL(poetJar))
            .filter { it.qualifiedName != null }
            .forEach { println(it.qualifiedName) }
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

fun listKlasses(url: URL): List<KClass<*>> {
    ZipInputStream(url.openStream()).use { zip ->
        URLClassLoader(arrayOf(url)).use { loader ->
            val res = mutableListOf<KClass<*>>()
            var entry = zip.nextEntry
            while (entry != null) {
                if (!entry.isDirectory && entry.name.indexOf(".class") >= 0) {
                    res.add(loader.loadClass(qualifiedName(entry)).kotlin)
                }
                entry = zip.nextEntry
            }
            return res;
        }
    }
}

fun qualifiedName(entry: ZipEntry) = entry
        .name
        .replace('/', '.') // including ".class"
        .let { name -> name.substring(0, name.length - ".class".length) }
