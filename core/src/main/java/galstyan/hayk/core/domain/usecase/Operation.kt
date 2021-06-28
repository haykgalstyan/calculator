package galstyan.hayk.core.domain.usecase


interface Operation {
    suspend operator fun invoke()
}