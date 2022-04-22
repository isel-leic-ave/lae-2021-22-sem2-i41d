package pt.isel

import org.junit.Test
import kotlin.reflect.KProperty
import kotlin.reflect.full.declaredMemberProperties
import kotlin.test.assertEquals

class DynamicLoggerTest {

    @Test fun testDynamicGetter() {
        val bal: KProperty<*> = SavingsAccount::class
            .declaredMemberProperties
            .find { it.name == "balance" }
            ?: throw Exception("SavingsAccount without a property balance!!!")
        /*
        val getterBalanceKlassFile = buildGetterProperty(SavingsAccount::class, bal)
        val out = PrinterStringBuffer()
        val getterBalance = loadAndCreateInstance(getterBalanceKlassFile, out) as Getter
        getterBalance.readAndPrint(SavingsAccount(2733, 2.5))
        assertEquals("balance = 2733,", out.buffer.toString())
        */
    }
}
