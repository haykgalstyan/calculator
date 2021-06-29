package galstyan.hayk.calculator.framework.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import galstyan.hayk.calculator.Logger
import galstyan.hayk.calculator.domain.Calculator
import galstyan.hayk.calculator.domain.CalculatorSimple
import galstyan.hayk.calculator.framework.log.NamedAndroidDebugLogger



@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    fun provideLogger(): Logger = NamedAndroidDebugLogger("AppLogger")

    @Provides
    fun provideCalculator(): Calculator = CalculatorSimple()
}