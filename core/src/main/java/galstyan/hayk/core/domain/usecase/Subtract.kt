package galstyan.hayk.core.domain.usecase

import galstyan.hayk.core.data.CalculationRepository


class Subtract(private val repository: CalculationRepository) : Operation {
    override suspend operator fun invoke() = repository.subtract()
}