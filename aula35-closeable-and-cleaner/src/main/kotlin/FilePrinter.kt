import java.io.Closeable
import java.io.FileOutputStream
import java.lang.ref.Cleaner
import java.lang.ref.Cleaner.Cleanable

const val threshold = 16

val cleaner: Cleaner = Cleaner.create()

class FilePrinterClenable(val file: FileOutputStream) : Runnable {
    override fun run() {
        file.close()
    }
}

/**
 * What to do when we have a Closeable class???
 * R: We have to implement Closeable too and override close()
 *
 * NOTICE this is a naif approach. StringBuilder is not the most effective buffer.
 */
class FilePrinter(path: String) : Closeable {
    private val file = FileOutputStream(path)
    private val buffer = StringBuilder()
    private val register: Cleanable = cleaner.register(this, FilePrinterClenable(file))

    fun print(msg: String) {
        buffer.append(msg)
        if(buffer.length > threshold) {
            file.write(buffer.toString().toByteArray())
            buffer.clear()
        }
    }
    /**
     * CALL cleanup code via register that will remove the
     * FilePrinterClenable object from the cleaner.
     * Otherwise, you may get the cleanup called twice!!!
     */
    override fun close() {
        register.clean()
    }
    /**
     * We call the close() of all properties Objects that are Closeable.
     * !!!! ATTENTION control execution with a flag.
     * This function can be called more than once, i.e. by Finalize
     */
    /*
    override fun close() {
        if(buffer.isNotEmpty()) {
            // May throw an Exception if the out stream is already closed.
            //
            out.write(buffer.toString().toByteArray())
        }
        out.close()
    }*/
    /**
     * DEPRECATED since Java 1.9 => DON'T use it
     */
     /*
    protected fun finalize() {
        println("# FINALIZING...#")
        close()
    }
    */
}

fun main() {
    demo2()
    println("Before GC and finalization...")
    readLine()
    /**
     * DON'T do this in your code
     */
    System.gc()
    System.runFinalization()
    println("After GC and finalization...")
    readLine()
}
/**
 * Internal buffer is not full, thus we loose the text.
 */
fun demo1() {
    val printer = FilePrinter("out.txt")
    printer.print("LAE course: ")
}
/**
 * Internal buffer is not full, but use() will call close() at completion.
 */
fun demo2() {
    FilePrinter("out.txt").use {
        it.print("LAE course: ")
    } // implicit call to it.close()
}

fun demo3() {
    FilePrinter("out.txt").print("LAE course: ")
    println("Before GC and finalization...")
    readLine()
    /**
     * DON'T do this in your code
     */
    System.gc()
    System.runFinalization()
    println("After GC and finalization...")
    readLine()
}
