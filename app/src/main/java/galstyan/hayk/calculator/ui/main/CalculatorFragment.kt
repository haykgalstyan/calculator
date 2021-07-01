package galstyan.hayk.calculator.ui.main

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import dagger.hilt.android.AndroidEntryPoint
import galstyan.hayk.calculator.databinding.FragmentCalculatorBinding
import galstyan.hayk.calculator.databinding.ViewCalculatorActionBinding
import galstyan.hayk.calculator.domain.Add
import galstyan.hayk.calculator.domain.Divide
import galstyan.hayk.calculator.domain.Multiply
import galstyan.hayk.calculator.domain.Subtract
import galstyan.hayk.calculator.ui.ViewBindingFragment


@AndroidEntryPoint
class CalculatorFragment : ViewBindingFragment<FragmentCalculatorBinding>() {
    override val bindingInflater: (LayoutInflater, ViewGroup?, Boolean) -> FragmentCalculatorBinding =
        FragmentCalculatorBinding::inflate

    private val viewModel: CalculatorViewModel by viewModels()


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        addCalculatorActionViews(createActionsList())

        viewModel.operationsText.observe(viewLifecycleOwner, { binding.input.text = it })
        viewModel.resultText.observe(viewLifecycleOwner, { binding.output.text = it })
    }


    data class UiAction(val text: String, val action: () -> Unit)


    private fun createActionsList() = ArrayList<UiAction>().apply {
        add(UiAction("7") { viewModel.onNumber("7") })
        add(UiAction("8") { viewModel.onNumber("8") })
        add(UiAction("9") { viewModel.onNumber("9") })
        add(UiAction("+") { viewModel.onAction(Add::class) })

        add(UiAction("4") { viewModel.onNumber("4") })
        add(UiAction("5") { viewModel.onNumber("5") })
        add(UiAction("6") { viewModel.onNumber("6") })
        add(UiAction("-") { viewModel.onAction(Subtract::class) })

        add(UiAction("1") { viewModel.onNumber("1") })
        add(UiAction("2") { viewModel.onNumber("2") })
        add(UiAction("3") { viewModel.onNumber("3") })
        add(UiAction("*") { viewModel.onAction(Multiply::class) })

        add(UiAction("AC") { viewModel.clearAll() })
        add(UiAction("0") { viewModel.onNumber("0") })
        add(UiAction("=") { viewModel.executeCalculation() })
        add(UiAction("/") { viewModel.onAction(Divide::class) })
    }


    private fun addCalculatorActionViews(actions: List<UiAction>) {
        actions.forEach { action ->
            val vbAction = ViewCalculatorActionBinding.inflate(layoutInflater)
            vbAction.action.text = action.text
            vbAction.action.setOnClickListener { action.action.invoke() }
            binding.actionsGridLayout.addView(vbAction.root)
        }
    }
}