package com.candela.lift

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Checkbox
import androidx.compose.material3.CheckboxDefaults
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.candela.lift.data.EjercicioData
import com.candela.lift.pantallaRutinas.ui.RutinasViewModel

// tarjeta que se muestra en la pantalla DescripcionRutina

@Composable
fun CardEjercicioGuardado(ejercicio: EjercicioData, viewModel: RutinasViewModel) {

    Card(
        modifier = Modifier.fillMaxWidth().padding(vertical = 5.dp),
        colors = CardDefaults.cardColors(containerColor = Color.White)
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            Text(
                text = ejercicio.nombre,
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold
                )
            Spacer(modifier = Modifier.height(8.dp))
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text(text = "Serie", fontWeight = FontWeight.Medium, modifier = Modifier.weight(1f))
                Text(text = "Peso", fontWeight = FontWeight.Medium, modifier = Modifier.weight(1f))
                Text(text = "Reps", fontWeight = FontWeight.Medium, modifier = Modifier.weight(1f))
                Text(text = "Listo", fontWeight = FontWeight.Medium, modifier = Modifier.weight(0.5f))
            }
            HorizontalDivider(modifier = Modifier.padding(vertical = 4.dp))
            ejercicio.series.forEach { serie ->
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 4.dp),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Text(text = "${serie.numeroSerie}", modifier = Modifier.weight(1f))
                    Text(text = "${serie.kg}", modifier = Modifier.weight(1f))
                    Text(text = "${serie.reps}", modifier = Modifier.weight(1f))

                    Checkbox(
                        checked = serie.checked,
                        onCheckedChange = {isChecked ->
                            viewModel.cambiarEstadoSerie(
                                ejercicioId = ejercicio.id,
                                serieId = serie.id,
                                completado = isChecked
                            )
                        },
                        colors = CheckboxDefaults.colors(checkedColor = Color(0xFF4CAF50)),
                        modifier = Modifier.weight(0.5f)
                    )
                }
            }
        }
    }
}