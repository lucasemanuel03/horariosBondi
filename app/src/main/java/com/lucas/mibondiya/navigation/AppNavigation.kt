package com.lucas.mibondiya.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHost
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.lucas.mibondiya.screens.AddHorario
import com.lucas.mibondiya.screens.HorariosNowScreen
import com.lucas.mibondiya.screens.MainScreen

@Composable
fun AppNavigation(){
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = AppScreens.MainScreen.route){
        composable(route = AppScreens.MainScreen.route){
            MainScreen(navController)
        }
        composable(route = AppScreens.AddHorario.route){
            AddHorario(navController)
        }
        composable(route = AppScreens.HorariosNowScreen.route + "/{opcion}"){
            backStackEntry -> val opcion = backStackEntry.arguments?.getString("opcion") ?: ""

            HorariosNowScreen(navController, opcion)
        }
    }
}