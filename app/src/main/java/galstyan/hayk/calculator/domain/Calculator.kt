package galstyan.hayk.calculator.domain

import java.math.BigDecimal


interface Calculator {

    fun add(operation: Operation)

    fun clear()

    fun executeWith(lastValue: BigDecimal): BigDecimal
}