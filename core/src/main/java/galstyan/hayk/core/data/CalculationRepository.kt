package galstyan.hayk.core.data

import galstyan.hayk.core.calculator.Calculator
import galstyan.hayk.core.domain.entity.CalculationResult
import galstyan.hayk.core.domain.entity.Operation


class CalculationRepository(
    private val calculator: Calculator
) {

    suspend fun addOperation(operation: Operation) {
        calculator.addOperation(operation)
    }

    suspend fun clearAll() {
        calculator.clearAll()
    }

    suspend fun execute(): CalculationResult {
        return calculator.execute()
    }
}