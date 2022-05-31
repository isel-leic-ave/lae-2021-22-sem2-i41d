package pt.isel

import java.io.FileOutputStream
import java.nio.file.FileSystemException
import kotlin.io.path.Path
import kotlin.io.path.deleteIfExists
import kotlin.test.Test
import kotlin.test.assertFailsWith
import kotlin.test.assertTrue

private const val OUT = "out.txt"

class CloseResourcesTest {

    @Test
    fun `test succeed to delete file after finalization run`() {
        /*
         * FileOutputStream keeps a handle to the native resource.
         */
        FileOutputStream(OUT)
        /*
         * We cannot delete corresponding File out.txt until the handle is released!
         */
        assertFailsWith<FileSystemException> {
            println(Path(OUT).deleteIfExists())
        }
        /*
         * Running GC will put FileOutputStream object in the Finalization queue.
         * Running finalization will release the handle hold by FileOutputStream.
         */
        System.gc()
        System.runFinalization()
        /*
         * Once the handle is release we can safely delete out.txt file.
         */
        assertTrue(Path(OUT).deleteIfExists())
    }

    @Test
    fun `test succeed to delete file after use block`() {
        /*
         * FileOutputStream keeps a handle to the native resource.
         */
        FileOutputStream(OUT).use {
            /*
             * We cannot delete corresponding File out.txt until the handle is released!
             */
            assertFailsWith<FileSystemException> {
                println(Path(OUT).deleteIfExists())
            }
        }
        /*
         * Once the handle is release we can safely delete out.txt file.
         */
        assertTrue(Path(OUT).deleteIfExists())
    }

    @Test
    fun `test succeed to delete file after close on finally`() {
        /*
         * FileOutputStream keeps a handle to the native resource.
         */
        val fo = FileOutputStream(OUT)
        try {
            /*
             * We cannot delete corresponding File out.txt until the handle is released!
             */
            assertFailsWith<FileSystemException> {
                println(Path(OUT).deleteIfExists())
            }
        } finally {
            fo.close()
        }
        /*
         * Once the handle is release we can safely delete out.txt file.
         */
        assertTrue(Path(OUT).deleteIfExists())
    }
}
