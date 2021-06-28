package galstyan.hayk.core.domain.usecase

import galstyan.hayk.core.calculator.Calculator
import galstyan.hayk.core.calculator.Multiply
import galstyan.hayk.core.domain.entity.Operand


class Multiplication(private val repository: Calculator) {
    suspend operator fun invoke(operand: Operand) = repository.addOperation(Multiply(operand))
}