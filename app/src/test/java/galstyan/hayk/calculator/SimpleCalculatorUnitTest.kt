package galstyan.hayk.calculator


import galstyan.hayk.calculator.domain.*
import kotlinx.coroutines.runBlocking
import org.junit.Test

import org.junit.Assert.*
import java.math.BigDecimal


class SimpleCalculatorUnitTest {


    @Test
    fun addition_isCorrect() = with(CalculatorSimple()) {
        add(Add(BigDecimal.valueOf(10)))
        add(Add(BigDecimal.valueOf(5)))
        val result = executeWith(BigDecimal.valueOf(5))

        // 10 + 5 + 5 = 20
        assertEquals(result, BigDecimal.valueOf(20))
    }


    @Test
    fun subtraction_isCorrect() = with(CalculatorSimple()) {
        add(Subtract(BigDecimal.valueOf(20)))
        add(Subtract(BigDecimal.valueOf(5)))
        val result = executeWith(BigDecimal.valueOf(5))

        // 20 - 5 - 5 = 10
        assertEquals(result, BigDecimal.valueOf(10))
    }


    @Test
    fun multiplication_isCorrect() = with(CalculatorSimple()) {
        add(Multiply(BigDecimal.valueOf(10)))
        add(Multiply(BigDecimal.valueOf(10)))
        val result = executeWith(BigDecimal.valueOf(10))

        // 10 * 10 * 10 = 1000
        assertEquals(result, BigDecimal.valueOf(1000))
    }


    @Test
    fun division_isCorrect() = with(CalculatorSimple()) {
        add(Divide(BigDecimal.valueOf(1000)))
        add(Divide(BigDecimal.valueOf(10)))
        val result = executeWith(BigDecimal.valueOf(10))

        // 1000 / 10 / 10 = 10
        assertEquals(result, BigDecimal.valueOf(10))
    }


    @Test
    fun allOperations_isCorrect() = with(CalculatorSimple()) {
        add(Add(BigDecimal.valueOf(10)))
        add(Subtract(BigDecimal.valueOf(10)))
        add(Multiply(BigDecimal.valueOf(10)))
        add(Divide(BigDecimal.valueOf(10)))
        val result = executeWith(BigDecimal.valueOf(10))

        // 10 + 10 - 10 * 10 / 10 = 10
        assertEquals(result, BigDecimal.valueOf(10))
    }


    @Test
    fun clear_isCorrect() = with(CalculatorSimple()) {
        add(Add(BigDecimal.valueOf(10)))
        add(Add(BigDecimal.valueOf(10)))
        add(Add(BigDecimal.valueOf(10)))
        add(Add(BigDecimal.valueOf(10)))
        clear()
        add(Add(BigDecimal.valueOf(10)))
        val result = executeWith(BigDecimal.valueOf(10))

        // 10 + 10 = 20
        assertEquals(result, BigDecimal.valueOf(20))
    }

}