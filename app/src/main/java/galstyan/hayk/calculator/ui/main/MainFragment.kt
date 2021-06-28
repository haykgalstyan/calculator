package galstyan.hayk.calculator.ui.main

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import dagger.hilt.android.AndroidEntryPoint
import galstyan.hayk.calculator.R
import galstyan.hayk.calculator.databinding.FragmentMainBinding
import galstyan.hayk.calculator.framework.CalculatorRepoImpl
import galstyan.hayk.calculator.ui.ViewBindingFragment
import galstyan.hayk.core.domain.usecase.Add
import galstyan.hayk.core.domain.usecase.Operation
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import kotlin.coroutines.coroutineContext


@AndroidEntryPoint
class MainFragment : ViewBindingFragment<FragmentMainBinding>() {
    override val bindingInflater: (LayoutInflater, ViewGroup?, Boolean) -> FragmentMainBinding =
        FragmentMainBinding::inflate

    private val viewModel: MainViewModel by viewModels()


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        val a: Operation = Add(CalculatorRepoImpl())
    }
}