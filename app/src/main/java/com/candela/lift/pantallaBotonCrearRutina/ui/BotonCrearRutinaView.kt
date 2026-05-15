package com.candela.lift.pantallaBotonCrearRutina.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.input.rememberTextFieldState
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.rememberTopAppBarState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.runtime.traceEventEnd
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.candela.lift.CardRutina

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PantallaBotonCrearRutina(navController: NavController) {
    val scrollBehavior = TopAppBarDefaults.pinnedScrollBehavior(rememberTopAppBarState())

    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(
                title = {
                    Text(
                        text = "Crear Rutina",

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
                    Text(
                        text = "Guardar",
                        modifier = Modifier.padding(end = 10.dp),
                        color = Color(0xFF3D5AFE)
                    )
                },
                scrollBehavior = scrollBehavior
            )
        },
        content = { innerPadding ->
            BodyBotonCrearRutina(navController, innerPadding)
        }
    )
}

@Composable
fun BodyBotonCrearRutina(navController: NavController, paddingValues: PaddingValues) {
    var textNombreRutina by rememberSaveable { mutableStateOf("") }
    var textNombreEjercicio by rememberSaveable { mutableStateOf("") }
    var tarjetaVisible by remember { mutableStateOf(false) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(paddingValues)
            .background(Color(0xFFDEDEDE))
    ) {
        Spacer(modifier = Modifier.padding(10.dp))
        TextField(
            value = textNombreRutina,
            onValueChange = {textNombreRutina = it},
            label = { Text(text = "Nombre Rutina") },
            modifier = Modifier.fillMaxWidth().padding(10.dp)
        )
        Button(
            onClick = {tarjetaVisible != tarjetaVisible},
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

        if (tarjetaVisible) {
            Card(
                modifier = Modifier.fillMaxWidth().padding(16.dp),
                elevation = CardDefaults.cardElevation(defaultElevation = 8.dp)
            ) {
                Column(modifier = Modifier.fillMaxSize()) {
                    Row(
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        TextField(
                            value = textNombreEjercicio,
                            onValueChange = {textNombreEjercicio = it},
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
                    Button(
                        onClick = {},
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
                }
            }
        }
    }
}