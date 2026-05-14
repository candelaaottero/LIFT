package com.candela.lift.navigation

sealed class AppScreens (val route: String) {
    object PantallaInicio: AppScreens("pantalla_inicio")
    object PantallaRutinas: AppScreens("pantalla_rutinas")
    object PantallaDescripcionRutina: AppScreens("pantalla_descripcion_rutina")
}