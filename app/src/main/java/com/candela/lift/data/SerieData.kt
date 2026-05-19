package com.candela.lift.data

data class SerieData (
    val id: String,
    val numeroSerie: Int,
    var kg: String = "0",
    var reps: String = "0",
    var checked: Boolean = false
)
