package galstyan.hayk.core.calculator

import galstyan.hayk.core.domain.entity.CalculationResult
import galstyan.hayk.core.domain.entity.Operation
import galstyan.hayk.core.domain.entity.Operand


class Divide(private val right: Operand) : Operation() {
    override suspend fun invoke(left: Operand) = CalculationResult(left.value.divide(right.value))
}