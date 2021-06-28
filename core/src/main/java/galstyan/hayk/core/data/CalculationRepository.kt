package galstyan.hayk.core.data

import galstyan.hayk.core.domain.entity.CalculationResult


interface CalculationRepository {
    suspend fun add(): CalculationResult
    suspend fun subtract(): CalculationResult
    suspend fun divide(): CalculationResult
    suspend fun multiply(): CalculationResult
    suspend fun clearAll(): CalculationResult
}