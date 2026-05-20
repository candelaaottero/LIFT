package com.candela.lift.pantallaRutinas.ui

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.candela.lift.data.EjercicioData
import com.candela.lift.data.HistorialEntrenamientoData
import com.candela.lift.data.RutinasData
import java.util.UUID


class RutinasViewModel : ViewModel() {
    val listaRutinas = mutableStateListOf<RutinasData>()
    // variable que guarda la rutina que el usuario pulsa para ver sus detalles
    var rutinaSeleccionada by mutableStateOf<RutinasData?>(null)

    var entrenamientoActivo by mutableStateOf(false)
    val historialEntrenamientos = mutableStateListOf<HistorialEntrenamientoData>()
    var ultimoEntrenamiento by mutableStateOf<HistorialEntrenamientoData?>(null)

    @RequiresApi(Build.VERSION_CODES.O)
    fun finalizarEntrenamientoActual() {
        val rutinaActual = rutinaSeleccionada ?: return
        val seriesCompletadas = rutinaActual.ejercicios.flatMap { it.series }.count { it.checked }
        val registro = HistorialEntrenamientoData(
            nombreRutina = rutinaActual.nombreRutina,
            totalSeriesCompletadas = seriesCompletadas,
            rutinaCopia = rutinaActual.copy()
        )
        historialEntrenamientos.add(0, registro)
        entrenamientoActivo = false
    }

    fun agregarRutina(nombreRutina: String, ejerciciosClonados: List<EjercicioData>) {
        val nuevaRutina = RutinasData(
            id = UUID.randomUUID().toString(),
            nombreRutina = nombreRutina,
            ejercicios = ejerciciosClonados
        )
        listaRutinas.add(nuevaRutina)
    }

    fun cambiarEstadoSerie(ejercicioId: String, serieId: String, completado: Boolean) {
        val rutinaActual = rutinaSeleccionada ?: return
        val ejerciciosActualizados = rutinaActual.ejercicios.map { ejercicio ->
            if (ejercicio.id == ejercicioId) {
                val seriesActualizadas = ejercicio.series.map {serie ->
                    if (serie.id == serieId) {
                        serie.copy(checked = completado)
                    } else {
                        serie
                    }
                }
                ejercicio.copy(series = seriesActualizadas)
            } else {
                ejercicio
            }
        }
        val rutinaModificada = rutinaActual.copy(ejercicios = ejerciciosActualizados)
        rutinaSeleccionada = rutinaModificada

        val index = listaRutinas.indexOfFirst { it.id == rutinaActual.id }
        if (index != -1) {
            listaRutinas[index] = rutinaModificada
        }
    }
}