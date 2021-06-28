package galstyan.hayk.calculator.ui.main

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import dagger.hilt.android.AndroidEntryPoint
import galstyan.hayk.calculator.databinding.FragmentMainBinding
import galstyan.hayk.calculator.framework.AndroidCalculator
import galstyan.hayk.calculator.ui.ViewBindingFragment
import galstyan.hayk.core.domain.usecase.Addition
import galstyan.hayk.core.domain.entity.Operation


@AndroidEntryPoint
class MainFragment : ViewBindingFragment<FragmentMainBinding>() {
    override val bindingInflater: (LayoutInflater, ViewGroup?, Boolean) -> FragmentMainBinding =
        FragmentMainBinding::inflate

    private val viewModel: MainViewModel by viewModels()


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        val a: Operation = Addition(AndroidCalculator())
    }
}