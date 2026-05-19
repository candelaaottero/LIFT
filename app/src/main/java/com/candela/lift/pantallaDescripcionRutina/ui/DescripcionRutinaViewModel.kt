package com.candela.lift.pantallaDescripcionRutina.ui

import androidx.compose.runtime.mutableStateListOf
import androidx.lifecycle.ViewModel
import com.candela.lift.data.SerieData
import com.candela.lift.pantallaRutinas.ui.RutinasData
import java.util.UUID

data class EjercicioData(
    val id: String,
    val nombre: String,
    val series: List<SerieData> = emptyList()
)

class DescripcionRutinaViewModel : ViewModel() {
    var rutinas = mutableStateListOf<RutinasData>()
    val listaEjercicios = mutableStateListOf<EjercicioData>()

    fun obtenerRutinaPorId(id: String): RutinasData? {
        return rutinas.find {
            it.id == id
        }
    }
}