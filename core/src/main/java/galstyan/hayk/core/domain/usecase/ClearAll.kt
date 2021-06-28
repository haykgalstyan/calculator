package galstyan.hayk.core.domain.usecase

import galstyan.hayk.core.data.CalculationRepository


class ClearAll(private val repository: CalculationRepository): Operation {
    override suspend operator fun invoke() = repository.clearAll()
}