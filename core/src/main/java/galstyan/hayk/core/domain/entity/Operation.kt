package galstyan.hayk.core.domain.entity


abstract class Operation {
    abstract suspend operator fun invoke(left: Operand): CalculationResult
}