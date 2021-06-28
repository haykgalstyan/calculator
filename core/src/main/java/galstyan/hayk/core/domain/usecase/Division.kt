package galstyan.hayk.core.domain.usecase

import galstyan.hayk.core.calculator.Calculator
import galstyan.hayk.core.calculator.Divide
import galstyan.hayk.core.domain.entity.Operand


class Division(private val repository: Calculator) {
    suspend operator fun invoke(operand: Operand) = repository.addOperation(Divide(operand))
}