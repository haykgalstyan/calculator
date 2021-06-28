package galstyan.hayk.core.domain.usecase

import galstyan.hayk.core.calculator.Calculator


class Clearance(private val repository: Calculator) {
    suspend operator fun invoke() = repository.clearAll()
}