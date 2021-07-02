package galstyan.hayk.calculator


import galstyan.hayk.calculator.domain.*
import org.junit.Test
import org.junit.Assert.*
import java.lang.ArithmeticException
import java.math.BigDecimal


class SimpleCalculatorUnitTest {


    @Test
    fun addition_isCorrect() = with(CalculatorSimple()) {
        add(Add(BigDecimal.valueOf(10)))
        add(Add(BigDecimal.valueOf(5)))
        val result = executeWith(BigDecimal.valueOf(5))

        // 10 + 5 + 5 = 20
        assertEquals(BigDecimal.valueOf(20), result)
    }


    @Test
    fun subtraction_isCorrect() = with(CalculatorSimple()) {
        add(Subtract(BigDecimal.valueOf(20)))
        add(Subtract(BigDecimal.valueOf(5)))
        val result = executeWith(BigDecimal.valueOf(5))

        // 20 - 5 - 5 = 10
        assertEquals(BigDecimal.valueOf(10), result)
    }


    @Test
    fun multiplication_isCorrect() = with(CalculatorSimple()) {
        add(Multiply(BigDecimal.valueOf(10)))
        add(Multiply(BigDecimal.valueOf(10)))
        val result = executeWith(BigDecimal.valueOf(10))

        // 10 * 10 * 10 = 1000
        assertEquals(BigDecimal.valueOf(1000), result)
    }


    @Test
    fun division_isCorrect() = with(CalculatorSimple()) {
        add(Divide(BigDecimal.valueOf(1000), scale = 0))
        add(Divide(BigDecimal.valueOf(10), scale = 0))
        val result = executeWith(BigDecimal.valueOf(10))

        // 1000 / 10 / 10 = 10
        assertEquals(BigDecimal.valueOf(10), result)

        clear()

        add(Divide(BigDecimal.valueOf(1000), scale = 12))
        val resultHighScale = executeWith(BigDecimal.valueOf(100))

        // 1000 / 100 = 10
        assertEquals(BigDecimal("10.000000000000"), resultHighScale)
    }


    @Test
    fun divisionByZero_throws() = with(CalculatorSimple()) {
        add(Divide(BigDecimal.ONE))
        assertThrows(ArithmeticException::class.java) {
            executeWith(BigDecimal.ZERO)
        }
        Unit
    }


    @Test
    fun divisionOneByThree_isCorrect() = with(CalculatorSimple()) {
        add(Divide(BigDecimal.ONE, scale = 12))
        val result = executeWith(BigDecimal.valueOf(3))

        assertEquals(BigDecimal.valueOf(0.333_333_333_333), result)

        clear()

        add(Divide(BigDecimal.ONE))
        val result2 = executeWith(BigDecimal.valueOf(3))

        assertTrue(result2 > BigDecimal.ZERO)
    }


    @Test
    fun emptyExecute_isCorrect() = with(CalculatorSimple()) {
        executeWith(BigDecimal.ZERO)
        Unit
    }


    @Test
    fun allOperations_isCorrect() = with(CalculatorSimple()) {
        add(Add(BigDecimal.valueOf(10)))
        add(Subtract(BigDecimal.valueOf(10)))
        add(Multiply(BigDecimal.valueOf(10)))
        add(Divide(BigDecimal.valueOf(10), scale = 0))
        val result = executeWith(BigDecimal.valueOf(10))

        // 10 + 10 - 10 * 10 / 10 = 10
        assertEquals(BigDecimal.valueOf(10), result)
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
        assertEquals(BigDecimal.valueOf(20), result)
    }


    @Test
    fun operationCopy_isCorrect() {
        val op1 = Add(BigDecimal.valueOf(10))
        val op2 = op1.copy(BigDecimal.valueOf(10))

        assertEquals(op1.javaClass, op2.javaClass)
        assertEquals(op1.left, op2.left)
        assertEquals(op1.toString(), op2.toString())

        val divideOp1 = Divide(BigDecimal.valueOf(10), 5)
        val divideOp2 = divideOp1.copy(BigDecimal.valueOf(10))

        assertEquals(divideOp1.javaClass, divideOp2.javaClass)
        assertEquals(divideOp1.left, divideOp2.left)
        assertEquals(divideOp1.scale, divideOp2.scale)
        assertEquals(divideOp1.toString(), divideOp2.toString())
    }
}