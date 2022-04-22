package pt.isel

import com.squareup.javapoet.JavaFile
import java.io.File
import java.net.URLClassLoader
import javax.tools.ToolProvider
import kotlin.reflect.full.declaredFunctions


fun main() {
    val file = buildMyDynamic()
    val loadMyDynamic: Any = loadAndCreateInstance(file, 28)
    /*
    val res = loadMyDynamic::class
        .declaredFunctions
        .find { it.name == "mul" }
        ?.call(loadMyDynamic, 2);
     */
    val res = (loadMyDynamic as Multiplier).mul(2);
    println(res);
}


private val root = File("./build")
private val classLoader = URLClassLoader.newInstance(arrayOf(root.toURI().toURL()))
private val compiler = ToolProvider.getSystemJavaCompiler()

fun loadAndCreateInstance(source: JavaFile, vararg args: Any): Any {
    // Save source in .java file.
    source.writeToFile(root)

    // Compile source file.
    compiler.run(null, null, null, "${root.path}/${source.typeSpec.name}.java")

    // Load and instantiate compiled class.
    return classLoader
        .loadClass(source.typeSpec.name)
        .declaredConstructors[0]
        .newInstance(*args)
}
