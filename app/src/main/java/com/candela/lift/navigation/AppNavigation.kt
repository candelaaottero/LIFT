package com.candela.lift.navigation

import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.candela.lift.pantallaDescripcionRutina.ui.PantallaDescripcionRutina
import com.candela.lift.pantallaInicio.ui.PantallaInicio
import com.candela.lift.pantallaRutinas.ui.PantallaRutinas

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AppNavigation() {
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = AppScreens.PantallaInicio.route){
        composable(route = AppScreens.PantallaInicio.route) {
            PantallaInicio(navController)
        }
        composable(route = AppScreens.PantallaRutinas.route) {
            PantallaRutinas(navController)
        }
        composable(route = AppScreens.PantallaDescripcionRutina.route) {
            PantallaDescripcionRutina(navController)
        }
    }
}