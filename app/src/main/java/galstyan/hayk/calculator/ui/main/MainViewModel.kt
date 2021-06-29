package galstyan.hayk.calculator.ui.main

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import galstyan.hayk.calculator.Logger
import galstyan.hayk.calculator.domain.Add
import galstyan.hayk.calculator.domain.Calculator
import galstyan.hayk.calculator.domain.Operation
import galstyan.hayk.calculator.domain.Subtract
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.math.BigDecimal
import javax.inject.Inject


@HiltViewModel
class MainViewModel @Inject constructor(
    val logger: Logger,
    val calculator: Calculator,
) : ViewModel() {

    fun initCalculator() {



    }
}