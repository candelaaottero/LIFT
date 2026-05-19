package com.candela.lift

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Card
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.candela.lift.pantallaDescripcionRutina.ui.EjercicioData

// tarjeta que se muestra en la pantalla DescripcionRutina

@Composable
fun CardEjercicioGuardado(tarjeta: EjercicioData, navController: NavController) {
    val ejercicios by rememberSaveable { mutableStateOf(listOf<EjercicioData>()) }

    Card(
        modifier = Modifier.fillMaxWidth().padding(vertical = 5.dp)
    ) {
        LazyColumn(modifier = Modifier.padding(10.dp).height(200.dp)) {
            items(ejercicios) { texto ->


            }
        }
    }
}