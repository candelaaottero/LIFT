package com.candela.lift

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Home
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarDefaults
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.navigation.NavController
import com.candela.lift.navigation.AppScreens


@Composable
fun BottomBar(navController: NavController) {
    NavigationBar(windowInsets = NavigationBarDefaults.windowInsets) {
        NavigationBarItem(
            selected = true,
            onClick = { navController.navigate(AppScreens.PantallaInicio.route) },
            icon = {Icon(Icons.Default.Home, null)},
            label = { Text(text = "Inicio") }
        )
        NavigationBarItem(
            selected = false,
            onClick = {navController.navigate(AppScreens.PantallaRutinas.route)},
            icon = {Icon(Icons.Default.Favorite, null)},
            label = {Text(text = "Rutinas")}
        )
    }
}