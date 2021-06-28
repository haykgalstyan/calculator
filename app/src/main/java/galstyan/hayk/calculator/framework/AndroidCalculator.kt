package galstyan.hayk.calculator.framework

import galstyan.hayk.core.calculator.Calculator
import galstyan.hayk.core.domain.entity.CalculationResult
import galstyan.hayk.core.domain.entity.Operation
import java.util.*


class AndroidCalculator(
    private val operations: Queue<Operation>
) : Calculator {


    override suspend fun addOperation(operation: Operation) {
        operations.add(operation)
    }

    override suspend fun clearAll() {
        operations.clear()
    }

    override suspend fun execute(): CalculationResult {
        // TODO()
    }
}