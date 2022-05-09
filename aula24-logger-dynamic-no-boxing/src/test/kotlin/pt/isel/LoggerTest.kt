package pt.isel

import kotlin.test.Test
import kotlin.test.assertEquals

class PrinterStringBuilder : Printer {
    val buffer = StringBuilder()
    override fun print(msg: Any?) {
        buffer.append(msg)
    }
}

class LoggerTest {
    @Test fun testLogPointPropertiesReflect() {
        val out = PrinterStringBuilder()
        val logger = LoggerReflect(out)
        logPointProperties(logger, out)
    }

     @Test fun testLogAccountPropertiesReflect() {
        val out = PrinterStringBuilder()
        val logger = LoggerReflect(out)
        logAccountProperties(logger, out)
    }

    @Test fun testLogAccountPropertiesAndFunctionsReflect() {
        val out = PrinterStringBuilder()
        val logger = LoggerReflect(out, MembersKind.FUNCTIONS)
        logAccountPropertiesAndFunctions(logger, out)
    }

   @Test fun testLogPointPropertiesDynamic() {
        val out = PrinterStringBuilder()
        val logger = LoggerDynamic(out)
        logPointProperties(logger, out)
    }

     @Test fun testLogAccountPropertiesDynamic() {
        val out = PrinterStringBuilder()
        val logger = LoggerDynamic(out)
        logAccountProperties(logger, out)
    }

    @Test fun testLogAccountPropertiesAndFunctionsDynamic() {
        val out = PrinterStringBuilder()
        val logger = LoggerDynamic(out, MembersKind.FUNCTIONS)
        logAccountPropertiesAndFunctions(logger, out)
    }

    fun logPointProperties(logger: AbstractLogger, out: PrinterStringBuilder) {
        logger.log(Point(5, 7))
        assertEquals("Point(x = 5, y = 7, )${System.lineSeparator()}", out.buffer.toString())
    }

    fun logAccountProperties(logger: AbstractLogger, out: PrinterStringBuilder) {
        val expected = "SavingsAccount(balance = 1000, )${System.lineSeparator()}"
        val a = SavingsAccount(1000, 2.5)
        logger.log(a)
        assertEquals(expected, out.buffer.toString())
    }

    fun logAccountPropertiesAndFunctions(logger: AbstractLogger, out: PrinterStringBuilder) {
        val expected = "SavingsAccount(balance() = 1000, monthlyInterest() = 208, )${System.lineSeparator()}"
        val a = SavingsAccount(1000, 2.5)
        logger.log(a)
        assertEquals(expected, out.buffer.toString())
    }



    @Test fun testLogForConsole() {
        val logger = LoggerReflect()
        logger.log(Student(8376473, "Ze Manel", Student.Address(713, "Rua das Papoilas")))
    }
}

