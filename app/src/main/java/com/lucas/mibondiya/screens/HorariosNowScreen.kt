package com.lucas.mibondiya.screens

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.Card
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults.topAppBarColors
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.lucas.horariosbondi.service.Horario
import com.lucas.horariosbondi.service.MockDataService
import com.lucas.mibondiya.navigation.AppScreens

@OptIn(ExperimentalMaterial3Api::class)
@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun HorariosNowScreen(navController: NavController, opcion: String = ""){
    var modifier = Modifier
    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(
                title = { Text("Próximos Horarios $opcion") },
                navigationIcon = {
                    IconButton(onClick = {
                        navController.navigate(route = AppScreens.MainScreen.route) }) {
                        Icon(
                            imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                            contentDescription = "Localized description"
                        )
                    }
                },
                colors = topAppBarColors(
                    containerColor = MaterialTheme.colorScheme.primaryContainer,
                    titleContentColor = MaterialTheme.colorScheme.primary,
                ),

                )
        },
        content = { innerPadding ->
            ContenidoPrincipal(opcion, innerPadding, modifier)},
    )
}

@Preview(showSystemUi = true)
@Composable
fun ContenidoPrincipal(opcion: String = "",
                       padding: PaddingValues = PaddingValues(28.dp),
                       modifier: Modifier = Modifier){

    val datosMock = MockDataService()
    var horarios = listOf<Horario>()

    horarios = if (opcion == "Jesús Maria a Córdoba"){
        datosMock.getHorariosToCba()
    }else{
        datosMock.getHorariosToJM()
    }
    Column(modifier = Modifier
        .fillMaxSize()
        .padding(padding),
        horizontalAlignment = Alignment.CenterHorizontally,

    ) {
        LazyColumn {
            items(horarios) { horario ->
                CardHorario(horario, modifier)
            }
        }
    }
}



@Composable
fun CardHorario(horario: Horario, modifier: Modifier){
    val datosHorario = convertHorarioString(horario)

    Card(
        modifier = modifier
            .padding(7.dp)
            .fillMaxSize(),

    ){
        Column(modifier = Modifier.padding(16.dp)
        ) {
            Text(
                color = MaterialTheme.colorScheme.primary,
                text = datosHorario.Empresa)

            Row(modifier = modifier.padding(top = 10.dp, bottom = 10.dp)){
                Text(
                    style = MaterialTheme.typography.titleLarge,
                    text = "Sale: ")
                Text(
                    style = MaterialTheme.typography.titleLarge,
                    fontWeight = FontWeight.Bold,
                    text = datosHorario.horaSalida)
            }
            Row{
                Text(
                    color = MaterialTheme.colorScheme.secondary,
                    text = "Llega: ")
                Text(
                    color = MaterialTheme.colorScheme.secondary,
                    text = datosHorario.horaLlegada)
            }

        }
    }



}


// FUNCIONES NO COMPOSABLE
data class DatosHorarioStr(val horaSalida: String, val horaLlegada: String, val Empresa: String)

fun convertHorarioString(horario: Horario): DatosHorarioStr {
    val horaSalida = horario.horaSalida
    val horaLlegada = horario.horaLlegada
    val empresa = horario.empresa.nombre

    return DatosHorarioStr(horaSalida, horaLlegada, empresa)
}

