package com.candela.lift.pantallaRutinas.ui

import androidx.compose.runtime.mutableStateListOf
import androidx.lifecycle.ViewModel

data class RutinasData(
    val id: String,
    val nombreRutina: String
)
class RutinasViewModel : ViewModel() {
    val listaRutinas = mutableStateListOf<RutinasData>()

    fun agregarRutina(nombreRutina: String) {
        val nuevaRutina = RutinasData(
            id = java.util.UUID.randomUUID().toString(),
            nombreRutina = nombreRutina
        )
        listaRutinas.add(nuevaRutina)
    }
}