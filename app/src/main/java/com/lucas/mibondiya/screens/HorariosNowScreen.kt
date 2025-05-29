package com.lucas.mibondiya.screens

import android.annotation.SuppressLint
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults.topAppBarColors
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.lucas.horariosbondi.service.Horario
import com.lucas.horariosbondi.service.MockDataService
import com.lucas.mibondiya.navigation.AppScreens
import java.time.LocalTime
import java.time.format.DateTimeFormatter

@OptIn(ExperimentalMaterial3Api::class)
@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun HorariosNowScreen(navController: NavController, opcion: String = ""){
    var modifier = Modifier
    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(
                title = { Text("Horarios de $opcion") },
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
                modifier = Modifier.shadow(10.dp),

                )
        },
        content = { innerPadding ->
            ContenidoPrincipal(opcion, innerPadding, modifier)},
    )
}

@SuppressLint("NewApi")
@Preview(showSystemUi = true)
@Composable
fun ContenidoPrincipal(opcion: String = "",
                       padding: PaddingValues = PaddingValues(28.dp),
                       modifier: Modifier = Modifier){

    val datosMock = MockDataService()
    var horarios = listOf<Horario>()

    //HORA ACTUAL
    val formatter = DateTimeFormatter.ofPattern("HH:mm")
    val ahora = LocalTime.now()

    horarios = if (opcion == "Jesús Maria a Córdoba"){
        datosMock.getHorariosToCba()
    }else{
        datosMock.getHorariosToJM()
    }

    val horariosOrdenados = horarios.sortedBy {
        LocalTime.parse(it.horaSalida, formatter)
    }

    // Buscar el índice del primer horario futuro
    val primerFuturoIndex = horariosOrdenados.indexOfFirst {
        LocalTime.parse(it.horaSalida, formatter) > ahora
    }.coerceAtLeast(0)

    val listState = rememberLazyListState()
    LaunchedEffect(Unit) {
        listState.scrollToItem(primerFuturoIndex)
    }

    Column(
        modifier = Modifier
        .fillMaxSize()
        .padding(padding),
        horizontalAlignment = Alignment.CenterHorizontally,

    ) {
        LazyColumn(state = listState) {
            items(horariosOrdenados) { horario ->
                val yaPaso = LocalTime.parse(horario.horaSalida, formatter) < ahora
                CardHorario(horario, modifier, yaPaso)
            }
        }
    }
}



@Composable
fun CardHorario(horario: Horario, modifier: Modifier, yaPaso: Boolean){
    val datosHorario = convertHorarioString(horario)

    // BACKGROUND PARA HORARIOS QUE YA SALIERON Y NO
    val backgroundColor = if (yaPaso)
        MaterialTheme.colorScheme.surfaceVariant // gris claro
    else
        MaterialTheme.colorScheme.primary // normal

    ElevatedCard(
        colors = CardDefaults.cardColors(containerColor = backgroundColor),
        elevation = CardDefaults.cardElevation(
            defaultElevation = 6.dp
        ),
        modifier = modifier
            .padding(8.dp)
            .fillMaxSize(),

    ){
        Row(
            modifier = Modifier.padding(16.dp),
            verticalAlignment = Alignment.CenterVertically){

            Column(
                modifier = Modifier
                    .weight(1f),
            )
            {
                Text(
                    color = MaterialTheme.colorScheme.onPrimary,
                    text = datosHorario.empresa)

                Row(modifier = modifier.padding(top = 5.dp, bottom = 7.dp)){
                    Text(
                        style = MaterialTheme.typography.titleLarge,
                        color = MaterialTheme.colorScheme.onPrimary,
                        text = "Sale: ")
                    Text(
                        style = MaterialTheme.typography.titleLarge,
                        color = MaterialTheme.colorScheme.onPrimary,
                        fontWeight = FontWeight.Bold,
                        text = datosHorario.horaSalida)
                }
                Row{
                    Text(
                        color = MaterialTheme.colorScheme.onSecondary,
                        text = "Llega: ")
                    Text(
                        color = MaterialTheme.colorScheme.onSecondary,
                        text = datosHorario.horaLlegada)
                }

                Row{
                    Text(
                        color = MaterialTheme.colorScheme.onSecondary,
                        text = "Se anuncia a: ")
                    Text(
                        color = MaterialTheme.colorScheme.onSecondary,
                        text = datosHorario.seAnuncia)
                }

            }

            Image(
                painter = painterResource(id = datosHorario.idLogo), // reemplazá con tu imagen
                contentDescription = "logo_empresa",

                modifier = Modifier
                    .size(70.dp)
                    .shadow(6.dp, shape = CircleShape, clip = false)
                    .clip(CircleShape)
                    .fillMaxHeight()
                    .background(Color(0xFFFFFFFF))

            )

        }
    }



}


// FUNCIONES NO COMPOSABLE
data class DatosHorarioStr(val horaSalida: String,
                           val horaLlegada: String,
                           val empresa: String,
                           val idLogo: Int,
                           val seAnuncia: String
    )

fun convertHorarioString(horario: Horario): DatosHorarioStr {
    val horaSalida = horario.horaSalida
    val horaLlegada = horario.horaLlegada
    val empresa = horario.empresa.nombre
    val idLogo = horario.empresa.idImage
    val seAnuncia = horario.seAnuncia


    return DatosHorarioStr(horaSalida, horaLlegada, empresa, idLogo, seAnuncia)
}

