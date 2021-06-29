package galstyan.hayk.calculator.domain

import java.math.BigDecimal


abstract class Operation(val left: BigDecimal) {
    abstract operator fun invoke(right: BigDecimal): BigDecimal
    abstract fun copy(left: BigDecimal): Operation
}