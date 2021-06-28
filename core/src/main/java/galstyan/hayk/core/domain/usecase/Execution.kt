package galstyan.hayk.core.domain.usecase

import galstyan.hayk.core.calculator.Calculator


class Execution(private val repository: Calculator) {
    suspend operator fun invoke() = repository.execute()
}