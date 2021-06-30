package galstyan.hayk.calculator.ui.main

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import galstyan.hayk.calculator.Logger
import galstyan.hayk.calculator.domain.*
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


    private val _resultText = MutableLiveData<String>()
    val resultText: LiveData<String> get() = _resultText

    private val _operationsText = MutableLiveData<String>()
    val operationsText: LiveData<String> get() = _operationsText


    private val numberInputBuffer = StringBuilder()


    fun onNumber(numberInput: String) {
        addNumber(numberInput)
    }


    fun onAction(action: Action) {
        val op = parseInputWithActionAsOperation(action)
        op?.let { addOperation(it) }
    }


    fun executeCalculation() {
        parseInputAsNumber()?.let {
            val result = calculator.executeWith(it)
            updateResultText(result)
        }
    }


    fun clearAll() {
        calculator.clear()
        updateOperationsText()
        updateResultText(null)
    }


    private fun addNumber(input: String) {
        numberInputBuffer.append(input)
        updateOperationsText()
    }


    private fun addOperation(operation: Operation) {
        calculator.add(operation)
        updateOperationsText()
    }


    private fun updateResultText(value: BigDecimal?) {
        _resultText.value = value?.toString() ?: ""
    }


    private fun updateOperationsText() {
        _operationsText.value = calculator.getOperations().joinToString {
            "${it.left} ${it::class.asString()} "
        }
    }


    private fun parseInputWithActionAsOperation(action: Action): Operation? {
        return parseInputAsNumber()?.let {
            createOperation(action, it)
        }
    }


    private fun parseInputAsNumber(): BigDecimal? {
        val input = numberInputBuffer.toString()
        numberInputBuffer.clear()
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


    private fun Action.asString(): String =
        when (this) {
            Add::class -> "+"
            Subtract::class -> "-"
            Multiply::class -> "*"
            Divide::class -> "/"
            else -> ""
        }
}