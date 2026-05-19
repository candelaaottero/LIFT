package com.candela.lift.pantallaBotonCrearRutina.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.rememberTopAppBarState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.candela.lift.CardCrearEjercicio
import com.candela.lift.data.EjercicioData
import com.candela.lift.pantallaRutinas.ui.RutinasViewModel
import java.util.UUID

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PantallaBotonCrearRutina(navController: NavController, viewModel: RutinasViewModel, onRutinaAgregada: () -> Unit) {
    val scrollBehavior = TopAppBarDefaults.pinnedScrollBehavior(rememberTopAppBarState())
    var nombreRutina by remember { mutableStateOf("") }
    var listaEjercicios by remember { mutableStateOf(listOf<EjercicioData>()) }

    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(
                title = {
                    Text(
                        text = "Crear Rutina"
                        )
                },
                navigationIcon = {
                    IconButton(onClick = { navController.popBackStack() }) {
                        Icon(
                            imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                            contentDescription = "Arrow back"
                        )
                    }
                },
                actions = {
                    Button(
                        onClick = {
                            if (nombreRutina.isNotBlank()) {
                                viewModel.agregarRutina(
                                    nombreRutina,
                                    listaEjercicios
                                )
                                onRutinaAgregada()

                            }
                        },
                        colors = ButtonColors(
                            containerColor = Color(0xFF3D5AFE),
                            contentColor = Color.White,
                            disabledContainerColor = Color(0xFF3D5AFE),
                            disabledContentColor = Color(0xFF3D5AFE)
                        )
                    ) {
                        Text(
                            text = "Guardar",
                            color = Color.White
                        )
                    }
                },
                scrollBehavior = scrollBehavior
            )
        },
        content = { innerPadding ->
            BodyBotonCrearRutina(
                navController = navController,
                paddingValues = innerPadding,
                nombreRutina = nombreRutina,
                onNombreRutinaChange = { nombreRutina = it },
                ejercicios = listaEjercicios,
                onEjerciciosChange = { listaEjercicios = it }
            )
        }
    )
}

@Composable
fun BodyBotonCrearRutina(
    navController: NavController,
    paddingValues: PaddingValues,
    nombreRutina: String,
    onNombreRutinaChange: (String) -> Unit,
    ejercicios : List<EjercicioData>,
    onEjerciciosChange: (List<EjercicioData>) -> Unit) {

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(paddingValues)
            .background(Color(0xFFDEDEDE))
    ) {
        Spacer(modifier = Modifier.padding(10.dp))
        TextField(
            value = nombreRutina,
            onValueChange = onNombreRutinaChange,
            label = { Text(text = "Nombre Rutina") },
            modifier = Modifier
                .fillMaxWidth()
                .padding(10.dp)
        )
        Button(
            onClick = {
                val nuevaTarjeta = EjercicioData(
                    id = UUID.randomUUID().toString(),
                    nombre = "",
                    series = emptyList()
                )
                onEjerciciosChange(ejercicios + nuevaTarjeta)
            },
            colors = ButtonColors(
                containerColor = Color(0xFF3D5AFE),
                contentColor = Color.White,
                disabledContainerColor = Color(0xFF3D5AFE),
                disabledContentColor = Color(0xFF3D5AFE)
            ),
            modifier = Modifier
                .fillMaxWidth()
                .padding(10.dp)
        ) {
            Text(
                text = "+ Agregar Ejercicio",
                modifier = Modifier
                    .padding(horizontal = 10.dp, vertical = 5.dp)
                    .background(color = Color(0xFF3D5AFE))
            )
        }
        LazyColumn(modifier = Modifier.fillMaxWidth()) {
            itemsIndexed(ejercicios, key = { _, item -> item.id }) { index, ejercicioIndividual ->

                CardCrearEjercicio(
                    ejercicio = ejercicioIndividual,
                    onEjercicioChange = { ejercicioActualizado ->
                        val listaModificable = ejercicios.toMutableList()
                        listaModificable[index] = ejercicioActualizado
                        onEjerciciosChange(listaModificable)
                    }
                )
            }
        }
    }
}