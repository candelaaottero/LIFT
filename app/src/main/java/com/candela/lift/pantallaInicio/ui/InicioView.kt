package com.candela.lift.pantallaInicio.ui

import android.annotation.SuppressLint
import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.AccountCircle
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.material3.rememberTopAppBarState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.candela.lift.BottomBar
import com.candela.lift.data.HistorialEntrenamientoData
import com.candela.lift.pantallaRutinas.ui.RutinasViewModel


@RequiresApi(Build.VERSION_CODES.O)
@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@ExperimentalMaterial3Api
@Composable
fun PantallaInicio(navController: NavController, viewModel: RutinasViewModel) {
    val scrollBehavior = TopAppBarDefaults.pinnedScrollBehavior(rememberTopAppBarState())

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        text = "LIFT",
                        modifier = Modifier.padding(10.dp,0.dp,0.dp,0.dp),
                        color = Color(0xFF3D5AFE),
                        fontSize = 30.sp,
                        fontWeight = FontWeight.Bold,
                        fontStyle = FontStyle.Italic,
                        textAlign = TextAlign.Start
                    )
                },
                actions = {
                    IconButton(onClick = {}) {
                        Icon(
                            imageVector = Icons.Rounded.AccountCircle,
                            contentDescription = null
                        )
                    }
                },
                scrollBehavior = scrollBehavior)
        },
        content = { innerPadding ->
            BodyInicio(
                navController = navController,
                paddingValues = innerPadding,
                viewModel = viewModel
            )
        },
        bottomBar = { BottomBar(navController) }
    )
}

@RequiresApi(Build.VERSION_CODES.O)
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun BodyInicio(navController: NavController, paddingValues: PaddingValues, viewModel: RutinasViewModel) {
    val historial = viewModel.historialEntrenamientos

    val sheetState = rememberModalBottomSheetState()
    var mostrarBottomSheet by remember { mutableStateOf(false) }
    var entrenamientoSeleccionado by remember { mutableStateOf<HistorialEntrenamientoData?>(null) }


    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(paddingValues)
            .background(Color(0xFFDEDEDE))
            .padding(16.dp)
    ) {
        Text(
            text = "Mis Entrenamientos",
            fontSize = 30.sp,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.padding(start = 10.dp)
        )
    }

    if (historial.isEmpty()) {
        Box(
            modifier = Modifier.fillMaxWidth(),
            contentAlignment = Alignment.Center
        ) {
            Text(text = "No hay entrenamientos registrados aún", color = Color.Gray)
        }
    } else {
        LazyColumn(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.spacedBy(10.dp)
        ) {
            items(historial, key = {it.id}) { entrenamiento ->
                CardHistorialInicio(
                    registro = entrenamiento,
                    onClick = {
                        entrenamientoSeleccionado = entrenamiento
                        mostrarBottomSheet = true
                    }
                )
            }
        }
    }

    if (mostrarBottomSheet && entrenamientoSeleccionado != null) {
        ModalBottomSheet(
            onDismissRequest = { mostrarBottomSheet = false },
            sheetState = sheetState,
            containerColor = Color.White
        ) {
            ContenidoResumenBottomSheet(entrenamiento = entrenamientoSeleccionado!!)
        }
    }
}

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun CardHistorialInicio(registro: HistorialEntrenamientoData, onClick: () -> Unit) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .clickable { onClick() },
        colors = CardDefaults.cardColors(containerColor = Color.White),
        elevation = CardDefaults.cardElevation(defaultElevation = 2.dp)
    ) {
        Row(
            modifier = Modifier.padding(16.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Column {
                Text(text = registro.rutinaCopia.nombreRutina, fontSize = 18.sp, fontWeight = FontWeight.Bold)
                Spacer(modifier = Modifier.height(4.dp))
                Text(text = registro.obtenerFecha(), fontSize = 13.sp, color = Color.Gray)
            }
            Text(
                text = "${registro.totalSeriesCompletadas} Series",
                fontWeight = FontWeight.Bold,
                color = Color(0xFF3D5AFE),
                fontSize = 16.sp
            )
        }
    }
}

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun ContenidoResumenBottomSheet(entrenamiento: HistorialEntrenamientoData) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 24.dp)
            .padding(bottom = 32.dp)
    ) {
        Text(text = "Resumen de Entrenamiento", fontSize = 22.sp, fontWeight = FontWeight.Bold)
        Text(text = entrenamiento.obtenerFecha(), color = Color.Gray, fontSize = 14.sp)
        HorizontalDivider(modifier = Modifier.padding(vertical = 12.dp))

        // Desglose de cada ejercicio realizado con sus kilos cargados
        LazyColumn(verticalArrangement = Arrangement.spacedBy(12.dp)) {
            items(entrenamiento.rutinaCopia.ejercicios) { ejercicio ->
                Column {
                    Text(text = ejercicio.nombre, fontWeight = FontWeight.Bold, fontSize = 16.sp)

                    // Mostramos las series de este ejercicio específico
                    ejercicio.series.forEach { serie ->
                        Row(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(5.dp),
                            horizontalArrangement = Arrangement.SpaceBetween
                        ) {
                            Text(text = "Serie ${serie.numeroSerie}", color = Color.DarkGray)
                            Text(
                                text = "${serie.kg} kg x ${serie.reps} reps",
                                fontWeight = FontWeight.Medium,
                                color = if (serie.checked) Color(0xFF4CAF50) else Color.Gray // Verde si se completó
                            )
                        }
                    }
                }
            }
        }
    }
}
