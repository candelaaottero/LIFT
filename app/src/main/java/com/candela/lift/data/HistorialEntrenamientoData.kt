package com.candela.lift.data

import android.os.Build
import androidx.annotation.RequiresApi
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

data class HistorialEntrenamientoData @RequiresApi(Build.VERSION_CODES.O) constructor(
    val id: String = java.util.UUID.randomUUID().toString(),
    val nombreRutina: String,
    val fecha: LocalDateTime = LocalDateTime.now(),
    val totalSeriesCompletadas: Int,
    val rutinaCopia: RutinasData
) {
    @RequiresApi(Build.VERSION_CODES.O)
    fun obtenerFecha(): String {
        val formatear = DateTimeFormatter.ofPattern("dd MMM yyyy - HH:mm")
        return fecha.format(formatear)
    }
}