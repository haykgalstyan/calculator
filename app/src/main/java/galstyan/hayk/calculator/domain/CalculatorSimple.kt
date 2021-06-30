package galstyan.hayk.calculator.domain

import java.math.BigDecimal
import kotlin.collections.ArrayList


class CalculatorSimple(
    private val operations: ArrayList<Operation> = ArrayList()
) : Calculator {
    override fun add(operation: Operation) {
        operations.add(operation)
    }

    override fun add(operations: List<Operation>) {
        this.operations.addAll(operations)
    }

    override fun getOperations(): List<Operation> {
        return operations.toList()
    }

    override fun clear() {
        operations.clear()
    }

    override fun executeWith(lastValue: BigDecimal): BigDecimal {
        var result: BigDecimal = operations.first().left
        operations.forEachIndexed { index, operation ->
            val right: BigDecimal = operations.getOrNull(index + 1)?.left ?: lastValue
            result = operation.copy(result).invoke(right)
        }
        return result
    }
}