package com.candela.lift.navigation

import android.annotation.SuppressLint
import android.app.Activity
import androidx.activity.compose.LocalActivity
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.ViewModelStoreOwner
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.candela.lift.pantallaBotonCrearRutina.ui.PantallaBotonCrearRutina
import com.candela.lift.pantallaDescripcionRutina.ui.PantallaDescripcionRutina
import com.candela.lift.pantallaInicio.ui.PantallaInicio
import com.candela.lift.pantallaRutinas.ui.PantallaRutinas
import com.candela.lift.pantallaRutinas.ui.RutinasViewModel
import androidx.lifecycle.viewmodel.compose.viewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AppNavigation() {
    val navController = rememberNavController()
    val compartidoViewModel: RutinasViewModel = viewModel()

    NavHost(navController = navController, startDestination = AppScreens.PantallaInicio.route){
        composable(route = AppScreens.PantallaInicio.route) {
            PantallaInicio(navController)
        }
        composable(route = AppScreens.PantallaRutinas.route) {
            PantallaRutinas(navController, viewModel = compartidoViewModel)
        }
        composable(route = AppScreens.PantallaDescripcionRutina.route) { backStackEntry ->
            val rutinaId = backStackEntry.arguments?.getString("rutinaId") ?: ""
            PantallaDescripcionRutina(navController = navController, rutinaId = rutinaId)
        }
        composable(route = AppScreens.PantallaBotonCrearRutina.route) {
            PantallaBotonCrearRutina(
                navController,
                viewModel = compartidoViewModel,
                onRutinaAgregada = {navController.popBackStack()}
            )
        }
    }
}