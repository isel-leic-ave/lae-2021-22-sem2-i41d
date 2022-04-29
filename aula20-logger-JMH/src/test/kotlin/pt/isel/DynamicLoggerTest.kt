package pt.isel

import org.junit.Test
import pt.isel.getters.buildGetterPropertyDynamic
import kotlin.reflect.KProperty
import kotlin.reflect.full.declaredMemberProperties
import kotlin.test.assertEquals

class DynamicLoggerTest {

    @Test fun testDynamicGetter() {
        val bal: KProperty<*> = SavingsAccount::class
            .declaredMemberProperties
            .find { it.name == "balance" }
            ?: throw Exception("SavingsAccount without a property balance!!!")

        /**
         * Create a new Getter implementation for Savings class and blance property.
         * And instantiate the new Getter class.
         */
        val out = PrinterStringBuilder()
        val getterBalanceKlassFile = buildGetterPropertyDynamic(SavingsAccount::class, bal)
        getterBalanceKlassFile.writeTo(System.out)
        val getterBalance = loadAndCreateInstance(getterBalanceKlassFile, out) as Getter
        /**
         * Act and Assert.
         */
        getterBalance.readAndPrint(SavingsAccount(2733, 2.5))
        assertEquals("balance = 2733,", out.buffer.toString())

    }
}
