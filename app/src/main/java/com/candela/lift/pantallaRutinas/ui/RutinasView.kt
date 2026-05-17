package com.candela.lift.pantallaRutinas.ui

import android.annotation.SuppressLint
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.AccountCircle
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.rememberTopAppBarState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.candela.lift.BottomBar
import com.candela.lift.CardRutina
import com.candela.lift.navigation.AppScreens

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PantallaRutinas(navController: NavController, viewModel: RutinasViewModel) {
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
                scrollBehavior = scrollBehavior
            )
        },
        bottomBar = { BottomBar(navController) },
        content = { innerPadding ->
            BodyRutinas(
                navController = navController,
                paddingValues = innerPadding,
                viewModel = viewModel
            )
        }
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun BodyRutinas(navController: NavController, paddingValues: PaddingValues, viewModel: RutinasViewModel) {
    val listaRutinas = viewModel.listaRutinas

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(paddingValues)
            .background(Color(0xFFDEDEDE))
    ) {
        Spacer(modifier = Modifier.padding(16.dp))
        Text(
            text = "Mis Rutinas",
            fontSize = 30.sp,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.padding(start = 10.dp))
        Spacer(modifier = Modifier.padding(16.dp))
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 10.dp)
        ) {
            Button(
                onClick = {navController.navigate(AppScreens.PantallaBotonCrearRutina.route)},
                colors = ButtonColors(
                    containerColor = Color(0xFF3D5AFE),
                    contentColor = Color.White,
                    disabledContainerColor = Color(0xFF3D5AFE),
                    disabledContentColor = Color(0xFF3D5AFE)
                )
            ) {
                Text(
                    text = "+ Nueva Rutina",
                    modifier = Modifier
                        .padding(horizontal = 10.dp, vertical = 5.dp)
                        .background(color = Color(0xFF3D5AFE))
                )
            }
        }
        LazyColumn(
            modifier = Modifier.fillMaxSize().padding(10.dp)
        ) {
            items(listaRutinas, key = {it.id}) { tarjeta ->
                CardRutina(tarjeta)
            }
        }
    }
}
