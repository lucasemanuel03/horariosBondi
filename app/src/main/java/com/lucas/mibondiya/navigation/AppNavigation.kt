package com.lucas.mibondiya.navigation

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.runtime.Composable
import androidx.navigation.NavHost
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.lucas.mibondiya.screens.AddHorario
import com.lucas.mibondiya.screens.EditHorarioScreen
import com.lucas.mibondiya.screens.HorariosNowScreen
import com.lucas.mibondiya.screens.MainScreen
import com.lucas.mibondiya.screens.ShowHorariosForEditScreen

@RequiresApi(Build.VERSION_CODES.O)
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

        composable(route = AppScreens.ShowHorariosForEditScreen.route + "{opcion}"){
            backStackEntry -> val opcion = backStackEntry.arguments?.getString("opcion") ?: ""
            ShowHorariosForEditScreen(navController, opcion)
        }

        composable(route = AppScreens.EditHorarioScreen.route + "/{opcion}/{id}"){
                backStackEntry ->
            val opcion = backStackEntry.arguments?.getString("opcion") ?: ""
            val id = backStackEntry.arguments?.getString("id")?.toIntOrNull() ?: -1

            EditHorarioScreen(navController, opcion, id)
        }
    }
}