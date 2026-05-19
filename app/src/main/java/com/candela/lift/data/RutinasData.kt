package com.candela.lift.data

data class RutinasData(
    val id: String,
    val nombreRutina: String,
    val ejercicios: List<EjercicioData> = emptyList()
)
