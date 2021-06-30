package galstyan.hayk.calculator.ui.main

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import galstyan.hayk.calculator.Logger
import galstyan.hayk.calculator.domain.*
import java.math.BigDecimal
import javax.inject.Inject
import kotlin.reflect.KClass


@HiltViewModel
class CalculatorViewModel @Inject constructor(
    val logger: Logger,
    val calculator: Calculator,
) : ViewModel() {

    private val _resultText = MutableLiveData<String>()
    val resultText: LiveData<String> get() = _resultText

    private val _operationsText = MutableLiveData<String>()
    val operationsText: LiveData<String> get() = _operationsText


    fun onNumber(number: Int) {

    }

    fun onAction(action: KClass<*>) {
        when(action) {
             Add::class -> Unit
             Subtract::class -> Unit
             Multiply::class -> Unit
             Divide::class -> Unit
        }
    }


    fun addOperation(operation: Operation) {
        calculator.add(operation)
        // update _operationsText
    }

    fun executeWith(lastValue: BigDecimal) {
        val result = calculator.executeWith(lastValue)
        // update _resultText
    }

    fun clear() {
        val result = calculator.clear()
        // update _operationsText
        // update _resultText
    }
}