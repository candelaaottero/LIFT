package com.candela.lift

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.modifier.modifierLocalMapOf
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.candela.lift.data.RutinasData
import com.candela.lift.navigation.AppScreens
import com.candela.lift.pantallaRutinas.ui.RutinasViewModel

// tarjeta que se muestra en la pantallas de "Mis Rutinas"
@Composable
fun CardRutina(tarjeta: RutinasData, navController: NavController, viewModel: RutinasViewModel) {
    Card(
        modifier = Modifier.fillMaxWidth().padding(vertical = 5.dp),
        onClick = {
            viewModel.rutinaSeleccionada = tarjeta
            navController.navigate("pantalla_descripcion_rutina/${tarjeta.id}")
        }
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            Text(
                text = tarjeta.nombreRutina,
                fontSize = 18.sp,
                fontWeight = FontWeight.Bold,
                color = Color.Black
            )
            Text(
                text = "${tarjeta.ejercicios.size} Ejercicios",
                fontSize = 14.sp,
                color = Color.Gray
            )
        }
    }
}