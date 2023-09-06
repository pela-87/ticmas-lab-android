package com.curso.android.app.practica.appcomparar.view

import android.R
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.curso.android.app.practica.appcomparar.model.Palabras
import kotlinx.coroutines.launch

class MainViewModel : ViewModel() {
    val palabra: LiveData<Palabras> get() = _palabra
    private val _palabra = MutableLiveData<Palabras>(Palabras(""))

    fun compararTextos(text1: String, text2: String) {
        val pal1 = text1.toString()
        val pal2 = text2.toString()
        var tipoMensaje: Int = 0

        if (pal1.isNotEmpty() and pal2.isNotEmpty()) {
            if (pal1.length == pal2.length) {
                tipoMensaje = 2
                for (pos in 0..((pal1.length) - 1)) {
                    if (pal1[pos] != pal2[pos]) {
                        tipoMensaje = 1
                        break
                    }
                }
            } else {
                tipoMensaje = 3
            }
        } else {
            if (pal1.isEmpty() and pal2.isEmpty()) {
                tipoMensaje = 0
            } else {
                if (pal1.isEmpty()) {
                    tipoMensaje = 4
                }
                if (pal2.isEmpty()) {
                    tipoMensaje = 5
                }
            }
        }
        mensajeComparacion(tipoMensaje)
    }

    private fun mensajeComparacion(tipo: Int) {
        var mensaje: String
        when (tipo) {
            1 -> mensaje = "Lo siento no son iguales."
            2 -> mensaje = "Felicidades, son iguales..!!"
            3 -> mensaje = "No son iguales, ya que son de diferentes tamaños."
            4 -> mensaje = "No ingreso la primera palabra."
            5 -> mensaje = "No ingreso la segunda palabra."
            else -> {
                mensaje = "Complete ambos campos para comparar."
            }
        }
        upMensahe(mensaje)
    }

    private fun upMensahe(mjs: String) {
        viewModelScope.launch {
            _palabra.value = Palabras(mjs)
        }
    }
}