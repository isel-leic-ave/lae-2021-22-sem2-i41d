package pt.isel

import com.squareup.javapoet.FieldSpec
import com.squareup.javapoet.JavaFile
import com.squareup.javapoet.MethodSpec
import com.squareup.javapoet.TypeSpec
import javax.lang.model.element.Modifier


/**
 * Build a class MyDynamic equivalent to:
 * public final class MyDynamic {
     final int nr;
     public MyDynamic(int nr) {
        this.nr = nr;
     }
     public int mul(int other) {
        return this.nr * other;
     }
}
 */
fun buildMyDynamic(): JavaFile {
    TODO()
}
