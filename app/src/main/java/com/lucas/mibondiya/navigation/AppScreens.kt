package com.lucas.mibondiya.navigation

sealed class AppScreens(val route: String) {

    object MainScreen: AppScreens("main_screen")
    object AddHorario: AppScreens("add_horario")
    object HorariosNowScreen: AppScreens("horarios_now")

}