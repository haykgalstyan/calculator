package galstyan.hayk.calculator


import galstyan.hayk.calculator.domain.*
import kotlinx.coroutines.runBlocking
import org.junit.Test

import org.junit.Assert.*
import java.math.BigDecimal

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
class SimpleCalculatorUnitTest {

    private val calculator = CalculatorSimple()

    @Test
    fun addition_isCorrect() = runBlocking {
        calculator.add(Add(BigDecimal.valueOf(10)))
        calculator.add(Add(BigDecimal.valueOf(5)))
        val result = calculator.executeWith(BigDecimal.valueOf(5))

        assertEquals(result, BigDecimal.valueOf(20))
    }

    @Test
    fun subtraction_isCorrect() = runBlocking {
        calculator.add(Subtract(BigDecimal.valueOf(20)))
        calculator.add(Subtract(BigDecimal.valueOf(5)))
        val result = calculator.executeWith(BigDecimal.valueOf(5))

        assertEquals(result, BigDecimal.valueOf(10))
    }

    @Test
    fun multiplication_isCorrect() = runBlocking {
        calculator.add(Multiply(BigDecimal.valueOf(10)))
        calculator.add(Multiply(BigDecimal.valueOf(10)))
        val result = calculator.executeWith(BigDecimal.valueOf(10))

        assertEquals(result, BigDecimal.valueOf(1000))
    }

    @Test
    fun division_isCorrect() = runBlocking {
        calculator.add(Divide(BigDecimal.valueOf(1000)))
        calculator.add(Divide(BigDecimal.valueOf(10)))
        val result = calculator.executeWith(BigDecimal.valueOf(10))

        assertEquals(result, BigDecimal.valueOf(10))
    }

    @Test
    fun division_allOperations() = runBlocking {
        calculator.add(Add(BigDecimal.valueOf(10)))
        calculator.add(Subtract(BigDecimal.valueOf(10)))
        calculator.add(Multiply(BigDecimal.valueOf(10)))
        calculator.add(Divide(BigDecimal.valueOf(10)))
        val result = calculator.executeWith(BigDecimal.valueOf(10))

        // 10 + 10 - 10 * 10 / 10 = 10
        assertEquals(result, BigDecimal.valueOf(10))
    }
}