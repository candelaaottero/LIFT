package com.candela.lift.data

data class EjercicioData (
    val id: String,
    val nombre: String,
    val series: List<SerieData> = emptyList()
)