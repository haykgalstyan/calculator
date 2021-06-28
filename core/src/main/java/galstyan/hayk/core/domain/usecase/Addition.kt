package galstyan.hayk.core.domain.usecase

import galstyan.hayk.core.calculator.Add
import galstyan.hayk.core.data.CalculationRepository
import galstyan.hayk.core.domain.entity.Operand


class Addition(private val repository: CalculationRepository) {
    suspend operator fun invoke(operand: Operand) = repository.addOperation(Add(operand))
}