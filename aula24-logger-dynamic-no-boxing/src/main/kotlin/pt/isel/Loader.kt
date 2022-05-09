package pt.isel

import com.squareup.javapoet.JavaFile
import java.io.File
import java.net.URLClassLoader
import java.nio.file.Paths
import javax.tools.ToolProvider
import kotlin.io.path.deleteIfExists

val root = File("./build")
private val classLoader = URLClassLoader.newInstance(arrayOf(root.toURI().toURL()))
private val compiler = ToolProvider.getSystemJavaCompiler()

fun loadAndCreateInstance(source: JavaFile, vararg args: Any): Any {
    // Delete source code file and out file if exist
    val sourceFile = "${root.path}/${source.typeSpec.name}.java"
    Paths.get(sourceFile).deleteIfExists()
    Paths.get(root.path, source.typeSpec.name + ".class").deleteIfExists()

    // Save source in .java file.
    source.writeToFile(root)

    // Compile source file.
    compiler.run(null, null, null, sourceFile)

    // Load and instantiate compiled class.
    return classLoader
        .loadClass(source.typeSpec.name)
        .declaredConstructors[0]
        .newInstance(*args)
}
