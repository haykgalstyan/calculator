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
import galstyan.hayk.calculator.ui.ViewBindingFragment


@AndroidEntryPoint
class CalculatorFragment : ViewBindingFragment<FragmentCalculatorBinding>() {
    override val bindingInflater: (LayoutInflater, ViewGroup?, Boolean) -> FragmentCalculatorBinding =
        FragmentCalculatorBinding::inflate

    private val viewModel: CalculatorViewModel by viewModels()


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        addCalculatorActionViews(createActionsList())
    }


    data class Action(val text: String, val action: () -> Unit)


    private fun createActionsList(): List<Action> {
        val actions = ArrayList<Action>()
        actions.add(Action("7", action = { viewModel.onNumber(7) }))
        actions.add(Action("8", action = { viewModel.onNumber(8) }))
        actions.add(Action("9", action = { viewModel.onNumber(9) }))
        actions.add(Action("+", action = { viewModel.onAction(Add::class) }))
    }


    private fun addCalculatorActionViews(actions: List<Action>) {
        actions.forEach { action ->
            val vbAction = ViewCalculatorActionBinding.inflate(layoutInflater)
            vbAction.action.text = action.text
            vbAction.action.setOnClickListener { action.action }
            binding.actionsGridLayout.addView(vbAction.root)
        }
    }
}