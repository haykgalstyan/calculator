package galstyan.hayk.core.domain.usecase

import galstyan.hayk.core.calculator.Calculator
import galstyan.hayk.core.calculator.Subtract
import galstyan.hayk.core.domain.entity.Operand


class Subtraction(private val repository: Calculator) {
    suspend operator fun invoke(operand: Operand) = repository.addOperation(Subtract(operand))
}