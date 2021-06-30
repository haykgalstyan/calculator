package galstyan.hayk.calculator.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import dagger.hilt.android.AndroidEntryPoint
import galstyan.hayk.calculator.Logger
import galstyan.hayk.calculator.R
import galstyan.hayk.calculator.ui.main.CalculatorFragment
import galstyan.hayk.calculator.ui.util.push
import javax.inject.Inject


@AndroidEntryPoint
class AppActivity : AppCompatActivity(R.layout.activity) {

    @Inject
    lateinit var logger: Logger

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        if (savedInstanceState == null) push(CalculatorFragment())
    }
}