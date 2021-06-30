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


    private val _resultText = MutableLiveData("")
    val resultText: LiveData<String> get() = _resultText

    private val _operationsText = MutableLiveData("")
    val operationsText: LiveData<String> get() = _operationsText


    private val numberInputBuffer = StringBuilder()


    fun onNumber(numberInput: String) {
        addNumber(numberInput)
    }


    fun onAction(action: Action) {
        // what to do on add action if inputBuff is empty?
        // it gets empty when: execute, after onAction (this one is ok)

        // todo: fail fast if input number buffer is empty?

        val op = parseInputWithActionAsOperation(action, true)
        op?.let { addOperation(it) } // fixme: else?
    }


    fun executeCalculation() {
        parseInputAsNumber(false)?.let {
            val result = calculator.executeWith(it)
            _resultText.value = result.toString()

            logger.log("executeCalculation -> _operationsText before", _operationsText.value)

            val t = _operationsText.value
            _operationsText.value = "$t"

            logger.log("executeCalculation -> _operationsText after", _operationsText.value)
        }
    }


    fun clearAll() {
        calculator.clear()

        _resultText.value = ""
        _operationsText.value = ""
    }


    private fun addNumber(input: String) {
        logger.log("addNumber", input)

        numberInputBuffer.append(input)

        logger.log("addNumber -> _operationsText before", _operationsText.value)

        val t = _operationsText.value
        _operationsText.value = "$t$input"

        logger.log("addNumber -> _operationsText after", _operationsText.value)
    }


    private fun addOperation(operation: Operation) {
        logger.log("addOperation", operation.toString())

        calculator.add(operation)

        logger.log("addOperation -> _operationsText before", _operationsText.value)

        val t = _operationsText.value
        _operationsText.value = "$t${operation.asString()}"

        logger.log("addOperation -> _operationsText after", _operationsText.value)
    }


    private fun parseInputWithActionAsOperation(
        action: Action,
        cleanInputBuffer: Boolean
    ): Operation? {
        logger.log(
            "parseInputWithActionAsOperation -> _operationsText after",
            _operationsText.value
        )

        return parseInputAsNumber(cleanInputBuffer)?.let {
            createOperation(action, it)
        }
    }


    private fun parseInputAsNumber(cleanInputBuffer: Boolean): BigDecimal? {
        val input = numberInputBuffer.toString()

        logger.log("parseInputAsNumber -> cleaning input: $cleanInputBuffer", input)
        if (cleanInputBuffer)
            numberInputBuffer.clear()

        return try {
            logger.log("parseInputAsNumber -> try", input)

            input.toBigDecimal()
        } catch (e: NumberFormatException) {
            logger.log("parseInputAsNumber -> try FAIL", input)

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

    private fun Operation.asString(): String =
        when (this) {
            is Add -> "+"
            is Subtract -> "-"
            is Multiply -> "*"
            is Divide -> "/"
            else -> ""
        }
}