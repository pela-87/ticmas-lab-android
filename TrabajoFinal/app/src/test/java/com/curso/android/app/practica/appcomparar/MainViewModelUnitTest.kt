package com.curso.android.app.practica.appcomparar

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.curso.android.app.practica.appcomparar.view.MainViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.test.StandardTestDispatcher
import kotlinx.coroutines.test.advanceUntilIdle
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
class MainViewModelUnitTest {
    private  lateinit var viewModel : MainViewModel

    @get: Rule
    val instantTaskRule = InstantTaskExecutorRule()
    private var dispatcher = StandardTestDispatcher()

    @Before
    fun setup(){
        Dispatchers.setMain(dispatcher)
        viewModel = MainViewModel()
    }
    @After
    fun tearDown(){
        Dispatchers.resetMain()
    }

    @Test
    fun MainViewModel_EstadoInicial() = runTest{
        val values = viewModel.palabra.value?.MSJ

        assert(values.toString() == "")
    }

    @Test
    fun MainViewModel_TestBtnCompararExitosa() = runTest{
        launch {
            viewModel.compararTextos("asd","asd")
        }

        advanceUntilIdle()

        val values = viewModel.palabra.value?.MSJ
        assert(values.toString() == "Felicidades, son iguales..!!")
    }

    @Test
    fun MainViewModel_TestBtnCompararDesiguales() = runTest{
        launch {
            viewModel.compararTextos("asd","asD")
        }

        advanceUntilIdle()

        val values = viewModel.palabra.value?.MSJ
        assert(values.toString() == "Lo siento no son iguales.")
    }

    @Test
    fun MainViewModel_TestBtnCompararDistLenght() = runTest{
        launch {
            viewModel.compararTextos("asdd","asd")
        }

        advanceUntilIdle()

        val values = viewModel.palabra.value?.MSJ
        assert(values.toString() == "No son iguales, ya que son de diferentes tamaños.")
    }

    @Test
    fun MainViewModel_TestBtnCompararDistLenght2() = runTest{
        launch {
            viewModel.compararTextos("asd","asdss")
        }

        advanceUntilIdle()

        val values = viewModel.palabra.value?.MSJ
        assert(values.toString() == "No son iguales, ya que son de diferentes tamaños.")
    }

    @Test
    fun MainViewModel_TestBtnCompararEmptyTXT1() = runTest{
        launch {
            viewModel.compararTextos("","asd")
        }

        advanceUntilIdle()

        val values = viewModel.palabra.value?.MSJ
        assert(values.toString() == "No ingreso la primera palabra.")
    }

    @Test
    fun MainViewModel_TestBtnCompararEmptyTXT2() = runTest{
        launch {
            viewModel.compararTextos("prueba","")
        }

        advanceUntilIdle()

        val values = viewModel.palabra.value?.MSJ
        assert(values.toString() == "No ingreso la segunda palabra.")
    }

    @Test
    fun MainViewModel_TestBtnCompararEmpty() = runTest{
        launch {
            viewModel.compararTextos("","")
        }

        advanceUntilIdle()

        val values = viewModel.palabra.value?.MSJ
        assert(values.toString() == "Complete ambos campos para comparar.")
    }
}