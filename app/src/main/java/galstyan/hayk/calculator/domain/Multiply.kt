package galstyan.hayk.calculator.domain

import java.math.BigDecimal


class Multiply(left: BigDecimal) : Operation(left) {
    override fun invoke(right: BigDecimal) = left * right
    override fun copy(left: BigDecimal) = Multiply(left)
}