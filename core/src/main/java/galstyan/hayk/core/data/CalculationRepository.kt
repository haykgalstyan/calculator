package galstyan.hayk.core.data

import galstyan.hayk.core.domain.entity.CalculationResult


interface CalculationRepository {
    suspend fun add()
    suspend fun subtract()
    suspend fun divide()
    suspend fun multiply()
    suspend fun clearAll()
    suspend fun execute(): CalculationResult
}