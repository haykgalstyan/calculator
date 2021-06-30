package galstyan.hayk.calculator.domain

import java.math.BigDecimal


interface Calculator {

    fun add(operation: Operation)

    fun add(operations: List<Operation>)

    fun getOperations() : List<Operation>

    fun clear()

    fun executeWith(lastValue: BigDecimal): BigDecimal
}