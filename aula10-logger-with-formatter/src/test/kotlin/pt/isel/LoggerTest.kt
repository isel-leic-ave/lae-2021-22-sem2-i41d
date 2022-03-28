package pt.isel

import java.lang.StringBuilder
import kotlin.test.Test
import kotlin.test.assertEquals

class PrinterStringBuilder : Printer {
    val buffer = StringBuilder()
    override fun print(msg: Any?) {
        buffer.append(msg)
    }
}

class LoggerTest {
    @Test fun testLogPointProperties() {
        val out = PrinterStringBuilder()
        val logger = Logger(out)
        logger.log(Point(5, 7))
        assertEquals("Point(x = 5, y = 7, )${System.lineSeparator()}", out.buffer.toString())
    }

     @Test fun testLogAccountProperties() {
        val expected = "SavingsAccount(annualInterestRate = 2.5, balance = 1000, )${System.lineSeparator()}"
        val a = SavingsAccount(1000, 2.5)
        val out = PrinterStringBuilder()
        val logger = Logger(out)
        logger.log(a)
        assertEquals(expected, out.buffer.toString())
    }

    @Test fun testLogAccountPropertiesAndFunctions() {
        val expected = "SavingsAccount(annualInterestRate() = 2.5, balance() = 1000, monthlyInterest() = 208, )${System.lineSeparator()}"
        val a = SavingsAccount(1000, 2.5)
        val out = PrinterStringBuilder()
        val logger = Logger(out, MembersKind.FUNCTIONS)
        logger.log(a)
        assertEquals(expected, out.buffer.toString())
    }



    @Test fun testLogForConsole() {
        val logger = Logger()
        logger.log(Point(5, 7))
    }
}

