package galstyan.hayk.calculator.domain

import java.math.BigDecimal
import java.math.RoundingMode


class Divide(left: BigDecimal, val scale: Int = 12) : Operation(left) {
    override fun invoke(right: BigDecimal): BigDecimal =
        left.divide(right, scale, RoundingMode.HALF_EVEN)

    override fun copy(left: BigDecimal) = Divide(left, scale)
    override fun toString() = "Operation: ${javaClass.simpleName}, left: $left, scale: $scale "
}