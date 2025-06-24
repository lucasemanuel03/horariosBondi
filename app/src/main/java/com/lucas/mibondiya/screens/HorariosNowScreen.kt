package com.lucas.mibondiya.screens

import android.annotation.SuppressLint
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.rounded.CheckCircle
import androidx.compose.material.icons.rounded.KeyboardArrowDown
import androidx.compose.material.icons.rounded.KeyboardArrowUp
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
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.graphics.ColorMatrix
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.lucas.mibondiya.data.model.HorarioMock
import com.lucas.mibondiya.service.MockDataService
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

@Preview(showSystemUi = true)
@SuppressLint("NewApi")
@Composable
fun ContenidoPrincipal(opcion: String = "",
                       padding: PaddingValues = PaddingValues(28.dp),
                       @SuppressLint("ModifierParameter") modifier: Modifier = Modifier){


    var horarios = listOf<HorarioMock>()

    //HORA ACTUAL
    val formatter = DateTimeFormatter.ofPattern("HH:mm")
    val ahora = LocalTime.now()

    horarios = if (opcion == "Jesús Maria a Córdoba"){
        MockDataService.getHorariosToCba()
    }else{
        MockDataService.getHorariosToJM()
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
        .padding(padding),
        horizontalAlignment = Alignment.CenterHorizontally,

    ) {
        HeaderLeyenda()
        LazyColumn(state = listState) {
            items(horariosOrdenados) { horario ->
                val yaPaso = LocalTime.parse(horario.horaSalida, formatter) < ahora
                CardHorario(horario, modifier, yaPaso)
            }
        }
    }
}


@Composable
fun HeaderLeyenda() {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .shadow(10.dp)
            .background(color = MaterialTheme.colorScheme.secondaryContainer)
            .padding(horizontal = 20.dp, vertical = 7.dp), // mismo padding horizontal que la Card
        verticalAlignment = Alignment.CenterVertically
    ) {
        // Espacio para imagen (matchea con .size(66.dp) de la imagen en la Card)
        Spacer(modifier = Modifier.width(66.dp))

        // Espacio proporcional entre imagen y los textos (igual que .weight(1.1f) en la Card)
        Spacer(modifier = Modifier.weight(1f))

        Row(modifier = Modifier.weight(5f)) {
            Row(modifier = Modifier.weight(8f)){
                Icon(
                    imageVector = Icons.Rounded.KeyboardArrowUp,
                    contentDescription = "Sale",
                    tint = MaterialTheme.colorScheme.secondary
                )
                Spacer(modifier = Modifier.width(4.dp))
                Text(
                    text = "Sale",

                    style = MaterialTheme.typography.titleLarge,
                    color = MaterialTheme.colorScheme.secondary
                )
            }

            Spacer(modifier = Modifier.weight(1f))

            Row(modifier = Modifier.weight(8f)) {
                Icon(
                    imageVector = Icons.Rounded.KeyboardArrowDown,
                    contentDescription = "Sale",
                    tint = MaterialTheme.colorScheme.secondary
                )

                Text(
                    text = "Llega",
                    style = MaterialTheme.typography.titleLarge,
                    color = MaterialTheme.colorScheme.secondary
                )
            }
        }
    }
}



@Composable
fun CardHorario(horario: HorarioMock, modifier: Modifier, yaPaso: Boolean){
    val datosHorario = convertHorarioString(horario)

    // VARIABLES PARA ESTILO TARJETA HORARIO
    var backgroundColor = MaterialTheme.colorScheme.surfaceBright
    var fontColorSalida = MaterialTheme.colorScheme.primary
    var fontColorLlegada = MaterialTheme.colorScheme.secondary
    var colorLogo = ColorFilter.colorMatrix(ColorMatrix().apply { setToSaturation(1f) })

    // VOLVIENDO TODOS LOS COLORES A GRISES CUANDO HORA SALIDA > HORA ACTUAL
    if(yaPaso){
        backgroundColor = MaterialTheme.colorScheme.outlineVariant // gris claro
        fontColorSalida = MaterialTheme.colorScheme.outline
        fontColorLlegada = MaterialTheme.colorScheme.outline
        colorLogo = ColorFilter.colorMatrix(ColorMatrix().apply { setToSaturation(0f) })
    }

    ElevatedCard(
        colors = CardDefaults.cardColors(containerColor = backgroundColor),
        elevation = CardDefaults.cardElevation(
            defaultElevation = 3.dp
        ),
        modifier = modifier
            .padding(8.dp)
    ){
        Row(
            modifier = Modifier
                .padding(top = 10.dp, bottom = 10.dp, start = 20.dp, end = 20.dp),
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically)

        {
            Image(
                painter = painterResource(id = datosHorario.idLogo), // reemplazá con tu imagen
                contentDescription = "logo_empresa",
                colorFilter = colorLogo,
                modifier = Modifier
                    .size(66.dp)
                    .shadow(2.dp, shape = CircleShape, clip = false)
                    .clip(CircleShape)
                    .fillMaxHeight()
                    .background(Color(0xFFFFFFFF))
            )
             Spacer(modifier = Modifier.weight(1.1f))// Espacio entre imagen y textos
            Column(
                modifier = Modifier.weight(5f)
            )
            {
                Row(
                    modifier = modifier
                        .padding(bottom = 2.dp)
                ){

                    Text(
                        modifier = Modifier.weight(8f),
                        style = MaterialTheme.typography.headlineLarge,
                        color = fontColorSalida,
                        fontWeight = FontWeight.Bold,
                        text = datosHorario.horaSalida)


                    Spacer(modifier = Modifier.weight(1f))
                    Text(
                        modifier = Modifier.weight(8f),
                        style = MaterialTheme.typography.headlineLarge,
                        color = fontColorLlegada,
                        text = datosHorario.horaLlegada)
                }

                Row(verticalAlignment = Alignment.CenterVertically) {
                    Text(
                        style = MaterialTheme.typography.labelSmall,
                        color = MaterialTheme.colorScheme.primary,
                        text = "Por ${datosHorario.empresa}")

                    Spacer(modifier = Modifier.width(2.dp))
                    Icon(
                        imageVector = Icons.Rounded.CheckCircle,
                        modifier = modifier.size(12.dp),
                        contentDescription = "Sale",
                        tint = MaterialTheme.colorScheme.primary
                    )
                }


                Text(
                    style = MaterialTheme.typography.labelLarge,
                    color = MaterialTheme.colorScheme.secondary,
                    text = "Se anuncia a: ${datosHorario.seAnuncia}")



            }
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

fun convertHorarioString(horario: HorarioMock): DatosHorarioStr {
    val horaSalida = horario.horaSalida
    val horaLlegada = horario.horaLlegada
    val empresa = horario.empresa.nombre
    val idLogo = horario.empresa.idImage
    val seAnuncia = horario.seAnuncia


    return DatosHorarioStr(horaSalida, horaLlegada, empresa, idLogo, seAnuncia)
}

