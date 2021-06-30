package galstyan.hayk.calculator.ui.main

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import galstyan.hayk.calculator.Logger
import galstyan.hayk.calculator.domain.*
import java.lang.IllegalStateException
import java.lang.NumberFormatException
import java.math.BigDecimal
import javax.inject.Inject
import kotlin.reflect.KClass
import kotlin.text.StringBuilder


typealias Action = KClass<*>

@HiltViewModel
class CalculatorViewModel @Inject constructor(
    val logger: Logger,
    val calculator: Calculator,
) : ViewModel() {

    private val _resultText = MutableLiveData("")
    val resultText: LiveData<String> get() = _resultText

    private val _operationsText = MutableLiveData("")
    val operationsText: LiveData<String> get() = _operationsText

    private val numberInputBuffer = StringBuilder()


    fun onNumber(numberInput: String) {
        addNumber(numberInput)
    }

    fun onAction(action: Action) {
        val operation = parseInputWithActionAsOperation(action, true)
        operation?.let { addOperation(it) }
    }

    fun executeCalculation() {
        parseInputAsNumber(false)?.let {
            val result = calculator.executeWith(it)
            _resultText.value = result.toString()
            _operationsText.value = _operationsText.value
        }
    }

    fun clearAll() {
        calculator.clear()
        _resultText.value = ""
        _operationsText.value = ""
    }


    private fun addNumber(input: String) {
        numberInputBuffer.append(input)
        _operationsText.value = "${_operationsText.value}$input"
    }

    private fun addOperation(operation: Operation) {
        calculator.add(operation)
        _operationsText.value = "${_operationsText.value}${operation.asString()}"
    }

    private fun parseInputWithActionAsOperation(
        action: Action,
        cleanInputBuffer: Boolean
    ): Operation? {
        return parseInputAsNumber(cleanInputBuffer)?.let { createOperation(action, it) }
    }

    private fun parseInputAsNumber(cleanInputBuffer: Boolean): BigDecimal? {
        val input = numberInputBuffer.toString()
        if (cleanInputBuffer) numberInputBuffer.clear()
        return try {
            input.toBigDecimal()
        } catch (e: NumberFormatException) {
            null
        }
    }

    private fun createOperation(action: Action, leftValue: BigDecimal) = when (action) {
        Add::class -> Add(leftValue)
        Subtract::class -> Subtract(leftValue)
        Multiply::class -> Multiply(leftValue)
        Divide::class -> Divide(leftValue)
        else -> throw IllegalArgumentException("$action is not a supported action")
    }

    private fun Operation.asString(): String = when (this) {
        is Add -> "+"
        is Subtract -> "-"
        is Multiply -> "*"
        is Divide -> "/"
        else -> throw IllegalStateException("Operation.asString() not supported on $this")
    }
}