package com.candela.lift

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Checkbox
import androidx.compose.material3.CheckboxDefaults
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.candela.lift.data.EjercicioData
import com.candela.lift.data.SerieData

// tarjeta que se muestra al agregar un nuevo ejercicio al crear una rutina en la pantalla BotonCrearRutina
@Composable
fun CardCrearEjercicio(
    ejercicio: EjercicioData,
    onEjercicioChange: (EjercicioData) -> Unit
) {
    val series = ejercicio.series

    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp),
        elevation = CardDefaults.cardElevation(defaultElevation = 8.dp)
    ) {
        Column(modifier = Modifier.fillMaxWidth()) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically
            ) {
                OutlinedTextField(
                    value = ejercicio.nombre,
                    onValueChange = { nuevoNombre ->
                        onEjercicioChange(ejercicio.copy(nombre = nuevoNombre))
                    },
                    label = { Text(text = "Nombre Ejercicio") },
                    modifier = Modifier.padding(10.dp)
                )
                IconButton(onClick = {}) {
                    Icon(
                        imageVector = Icons.Filled.MoreVert,
                        contentDescription = null
                    )
                }
            }
            HorizontalDivider(thickness = 2.dp)
            Button(
                onClick = {
                    val nuevaSerie = SerieData(
                        id = java.util.UUID.randomUUID().toString(),
                        numeroSerie = series.size + 1,
                        kg = "",
                        reps = "",
                        checked = false
                    )
                    onEjercicioChange(ejercicio.copy(series = series + nuevaSerie))
                },
                colors = ButtonColors(
                    containerColor = Color(0xFF8F8E8E),
                    contentColor = Color.White,
                    disabledContainerColor = Color(0xFF8F8E8E),
                    disabledContentColor = Color(0xFF8F8E8E)
                ),
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(10.dp)
            ) {
                Text(
                    text = "+ Agregar Serie",
                    modifier = Modifier
                        .padding(horizontal = 10.dp, vertical = 5.dp)
                        .background(color = Color(0xFF8F8E8E))
                )
            }
            Row(
                modifier = Modifier.padding(10.dp)
            ) {
                Spacer(modifier = Modifier.padding(10.dp))
                Text(text = "Series", fontWeight = FontWeight.Bold)
                Spacer(modifier = Modifier.padding(10.dp))
                Text(text = "Kg", fontWeight = FontWeight.Bold)
                Spacer(modifier = Modifier.padding(10.dp))
                Text(text = "Repeticiones", fontWeight = FontWeight.Bold)
            }
            Column(modifier = Modifier.padding(10.dp)) {
                series.forEach { textoSeries ->
                    Row(
                        modifier = Modifier.padding(vertical = 4.dp),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        TextField(
                            value = textoSeries.numeroSerie.toString(),
                            onValueChange = {},
                            readOnly = true,
                            modifier = Modifier.width(50.dp)
                        )
                        Spacer(modifier = Modifier.width(8.dp))
                        TextField(
                            value = textoSeries.kg,
                            onValueChange = { nuevoValor ->
                                if (nuevoValor.all { it.isDigit() }) {
                                    val seriesModificadas = series.map {
                                        if (it.id == textoSeries.id) it.copy(kg = nuevoValor) else it
                                    }
                                    onEjercicioChange(ejercicio.copy(series = seriesModificadas))
                                }
                            },
                            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                            modifier = Modifier.width(70.dp)
                        )
                        Spacer(modifier = Modifier.width(8.dp))
                        TextField(
                            value = textoSeries.reps,
                            onValueChange = { nuevoValor ->
                                if (nuevoValor.all { it.isDigit() }) {
                                    val seriesModificadas = series.map {
                                        if (it.id == textoSeries.id) it.copy(reps = nuevoValor) else it
                                    }
                                    onEjercicioChange(ejercicio.copy(series = seriesModificadas))
                                }
                            },
                            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                            modifier = Modifier.width(70.dp)
                        )
                        Checkbox(
                            checked = textoSeries.checked,
                            onCheckedChange = { nuevoChecked ->
                                val seriesModificadas = series.map {
                                    if (it.id == textoSeries.id) it.copy(checked = nuevoChecked) else it
                                }
                                onEjercicioChange(ejercicio.copy(series = seriesModificadas))
                            },
                            modifier = Modifier.padding(start = 16.dp)
                        )
                    }
                }
            }
        }
    }
}