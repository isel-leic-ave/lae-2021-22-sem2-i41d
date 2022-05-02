package pt.isel.getters

import com.squareup.javapoet.FieldSpec
import com.squareup.javapoet.JavaFile
import com.squareup.javapoet.MethodSpec
import com.squareup.javapoet.TypeName
import com.squareup.javapoet.TypeSpec
import pt.isel.Getter
import pt.isel.Printer
import javax.lang.model.element.Modifier
import kotlin.reflect.KCallable
import kotlin.reflect.KClass
import kotlin.reflect.KProperty
import kotlin.reflect.jvm.javaMethod

fun buildGetterFunctionDynamic(domainKlass: KClass<*>, func: KCallable<*>): JavaFile {
        val fieldOut = FieldSpec.builder(Printer::class.java, "out")
                .addModifiers(Modifier.FINAL, Modifier.PRIVATE)
                .build()

        val ctor = MethodSpec.constructorBuilder()
                .addModifiers(Modifier.PUBLIC)
                .addParameter(Printer::class.java, "out")
                .addStatement("this.\$N = out", fieldOut)
                .build()

        val funcTargetName = if(func is KProperty) func.getter.javaMethod?.name
                else func.name

        val readAndPrint = MethodSpec.methodBuilder("readAndPrint")
                .addModifiers(Modifier.PUBLIC)
                .addParameter(Object::class.java, "target")
                .returns(TypeName.VOID)
                .addStatement(
                        "Object v = ((\$T) target).\$L()",
                        domainKlass.java,
                        funcTargetName)
                .addStatement("out.print(\"\$L() = \" + v + \", \")", func.name)
                .build()

        val getter = TypeSpec
                .classBuilder("Getter_Func_${domainKlass.simpleName}_${func.name}")
                .addModifiers(Modifier.PUBLIC)
                .addField(fieldOut)
                .addMethod(ctor)
                .addMethod(readAndPrint)
                .addSuperinterface(Getter::class.java)
                .build()

        return JavaFile.builder("", getter).build()
}

/**
 * Generate a class that implements Getter similar to:
 *
 * class Getter_Prop_SavingsAccount_balance implements Getter {
 *    private final Printer out;
 *    public Getter_Prop_SavingsAccount_balance(Printer out) {
 *      this.out = out;
 *    }
 *    public void readAndPrint(Object target) {
 *      Object v = ((SavingsAccount) target).getBalance();
 *      out.print("balance = " + v + ",");
 *    }
 *  }
 */
fun buildGetterPropertyDynamic(domainKlass: KClass<*>, prop: KProperty<*>): JavaFile {
        val fieldOut = FieldSpec.builder(Printer::class.java, "out")
                .addModifiers(Modifier.FINAL, Modifier.PRIVATE)
                .build()

        val ctor = MethodSpec.constructorBuilder()
                .addModifiers(Modifier.PUBLIC)
                .addParameter(Printer::class.java, "out")
                .addStatement("this.\$N = out", fieldOut)
                .build()

        val readAndPrint = MethodSpec.methodBuilder("readAndPrint")
                .addModifiers(Modifier.PUBLIC)
                .addParameter(Object::class.java, "target")
                .returns(TypeName.VOID)
                .addStatement(
                        "Object v = ((\$T) target).\$L()",
                        domainKlass.java,
                        prop.getter.javaMethod?.name)
                .addStatement("out.print(\"\$L = \" + v + \", \")", prop.name)
                .build()

        val getter = TypeSpec
                .classBuilder("Getter_Prop_${domainKlass.simpleName}_${prop.name}")
                .addModifiers(Modifier.PUBLIC)
                .addField(fieldOut)
                .addMethod(ctor)
                .addMethod(readAndPrint)
                .addSuperinterface(Getter::class.java)
                .build()

        return JavaFile.builder("", getter).build()
}
