package galstyan.hayk.core.calculator

import galstyan.hayk.core.domain.entity.CalculationResult
import galstyan.hayk.core.domain.entity.Operation


interface Calculator {
    suspend fun addOperation(operation: Operation)
    suspend fun clearAll()
    suspend fun execute(): CalculationResult
}