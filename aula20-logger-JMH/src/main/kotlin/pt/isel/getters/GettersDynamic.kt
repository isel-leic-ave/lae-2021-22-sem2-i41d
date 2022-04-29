package pt.isel.getters

import com.squareup.javapoet.JavaFile
import com.squareup.javapoet.TypeSpec
import pt.isel.Printer
import kotlin.reflect.KCallable
import kotlin.reflect.KClass
import kotlin.reflect.KProperty

fun buildGetterFunctionDynamic(out: Printer, func: KCallable<*>): JavaFile {
        TODO("Not yet implemented")
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
        val fieldOut = null // TPC

        val ctor = null // TPC

        val readAndPrint = null // TPC

        val getter = TypeSpec
                .classBuilder("Getter_Prop_${domainKlass.simpleName}_${prop.name}")
                .build()
        return JavaFile.builder("", getter).build()
}
