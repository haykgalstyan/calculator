package galstyan.hayk.calculator.domain

import java.math.BigDecimal


class Subtract(left: BigDecimal) : Operation(left) {
    override fun invoke(right: BigDecimal) = left - right
    override fun copy(left: BigDecimal) = Subtract(left)
    override fun toString() = "Operation: ${javaClass.simpleName}, left: $left "
}