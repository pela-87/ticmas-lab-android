package com.curso.android.app.practica.appcomparar.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.view.inputmethod.InputMethodManager
import androidx.activity.viewModels
import com.curso.android.app.practica.appcomparar.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private val mainViewModel: MainViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // setContentView(R.layout.activity_main)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        mainViewModel.palabra.observe(this) {
            binding.txtComparacion.text = "${it.MSJ}"
        }

        binding.btncomparar.setOnClickListener() {
            it.CerrarTeclado()
            mainViewModel.compararTextos(
                binding.etprimarytext.text.toString(),
                binding.etsecondtext.text.toString()
            )
        }
    }

    fun View.CerrarTeclado() {
        val imm = context.getSystemService(INPUT_METHOD_SERVICE) as InputMethodManager
        imm.hideSoftInputFromWindow(windowToken, 0)
    }
}