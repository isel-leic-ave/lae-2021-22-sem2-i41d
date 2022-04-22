package pt.isel

import com.squareup.javapoet.FieldSpec
import com.squareup.javapoet.JavaFile
import com.squareup.javapoet.MethodSpec
import com.squareup.javapoet.ParameterSpec
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
    val fieldNr = FieldSpec
        .builder(Int::class.java, "number")
        .addModifiers(Modifier.FINAL, Modifier.PRIVATE)
        .build()
    val ctor = MethodSpec
        .constructorBuilder()
        .addModifiers(Modifier.PUBLIC)
        .addParameter(Int::class.java, "nr")
        .addStatement("this.\$N = nr", fieldNr)
        .build()
    val mul = MethodSpec
        .methodBuilder("mul")
        .addModifiers(Modifier.PUBLIC)
        .addParameter(Int::class.java, "other")
        .returns(Int::class.java)
        .addStatement("return this.\$N * other", fieldNr)
        .build()

    val myDynamic = TypeSpec
        .classBuilder("MyDynamic")
        .addModifiers(Modifier.PUBLIC)
        .addSuperinterface(Multiplier::class.java)
        .addField(fieldNr)
        .addMethod(ctor)
        .addMethod(mul)
        .build();

    return JavaFile.builder("", myDynamic).build()
}
