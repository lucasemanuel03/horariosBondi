package com.lucas.mibondiya.screens

import android.os.Build
import android.util.Log
import androidx.annotation.RequiresApi
import androidx.compose.animation.animateContentSize
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.Create
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Info
import androidx.compose.material.icons.rounded.CheckCircle
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TopAppBarDefaults.topAppBarColors
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.lucas.mibondiya.data.model.HorarioCompleto
import com.lucas.mibondiya.navigation.AppScreens
import com.lucas.mibondiya.service.MockDataService
import com.lucas.mibondiya.viewModel.HorarioViewModel
import java.time.LocalTime
import java.time.format.DateTimeFormatter


@RequiresApi(Build.VERSION_CODES.O)
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ShowHorariosForEditScreen(navController: NavController, opcion: String = ""){

    val viewModelHorario : HorarioViewModel = hiltViewModel()


    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(
                title = { Text("Editar Horarios") },
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

            ContenidoPrincipal(navController, innerPadding, opcion, viewModelHorario)},
    )
}


@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun ContenidoPrincipal(navController: NavController,
                       innerPadding: PaddingValues,
                       opcion: String = "",
                       viewModelHorario: HorarioViewModel){


    // ==============================================================
    // TOMANDO LOS DATOS DESDE LA BASE DE DATOS SEGUN CIUDAD DESTINO
    val idCiudadDestino = if (opcion == "Jesús Maria a Córdoba") 2 else 1

    LaunchedEffect(idCiudadDestino) {
        viewModelHorario.setCiudadFin(idCiudadDestino)
    }
    val horarios by viewModelHorario.horarios.collectAsState()
    Log.d("HORARIOS", "Cantidad de horarios: ${horarios.size}")
    // =============================================================

    // === LOOP ESPERANDO Para no romper ===
    if (horarios.isEmpty()) {
        CircularProgressIndicator()
        return
    }

    val formatter = DateTimeFormatter.ofPattern("HH:mm")

    val horariosOrdenados = horarios.sortedBy {
        LocalTime.parse(it.horaSalida, formatter)
    }

    Column(
        modifier = Modifier
            .padding(innerPadding),
        horizontalAlignment = Alignment.CenterHorizontally,

        ) {
        HeaderLeyenda()
        LazyColumn() {
            items(horariosOrdenados) { horario ->
                CardHorarioToEdit(navController, horario, opcion, modifier = Modifier)
            }
        }
    }
}

@Composable
fun CardHorarioToEdit(navController: NavController, horario: HorarioCompleto, opcion: String, modifier: Modifier){
    var expandida by remember { mutableStateOf(false) }
    var openAlertDialog by remember { mutableStateOf(false) }

    // VARIABLES PARA ESTILO TARJETA HORARIO
    var backgroundColor = MaterialTheme.colorScheme.surfaceBright
    var fontColorSalida = MaterialTheme.colorScheme.primary
    var fontColorLlegada = MaterialTheme.colorScheme.secondary

    ElevatedCard(
        colors = CardDefaults.cardColors(containerColor = backgroundColor),
        modifier = modifier
            .padding(8.dp)
            .clickable { expandida = !expandida },
        elevation = CardDefaults.cardElevation(defaultElevation = 3.dp)
    ){
        Column(
            modifier = Modifier.animateContentSize()
        )
        {
            Row(
                modifier = Modifier
                    .padding(top = 10.dp, bottom = 10.dp, start = 20.dp, end = 20.dp)
                    ,
                horizontalArrangement = Arrangement.Center,
                verticalAlignment = Alignment.CenterVertically)

            {
                Image(
                    painter = painterResource(id = horario.empresaImagenId), // reemplazá con tu imagen
                    contentDescription = "logo_empresa",
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
                            text = horario.horaSalida)


                        Spacer(modifier = Modifier.weight(1f))
                        Text(
                            modifier = Modifier.weight(8f),
                            style = MaterialTheme.typography.headlineLarge,
                            color = fontColorLlegada,
                            text = horario.horaLlegada)
                    }

                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Text(
                            style = MaterialTheme.typography.labelSmall,
                            color = MaterialTheme.colorScheme.primary,
                            text = "Por ${horario.empresaNombre}")

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
                        text = "Se anuncia a: ${horario.seAnuncia}")
                }

            }
            if (expandida) {
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Card(
                        colors = CardDefaults.cardColors(
                            containerColor = MaterialTheme.colorScheme.surfaceContainer),
                        modifier = Modifier
                            .padding(8.dp)


                    ) {
                        Column(
                            modifier = Modifier
                                .padding(8.dp)

                        ) {
                            Text(
                                style = MaterialTheme.typography.titleMedium,
                                text = "Datos Extras del Horario:")
                            Spacer(modifier = Modifier.height(6.dp))
                            Text(text = horario.notas)
                        }
                    }

                    Row(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                        Button(
                            colors = ButtonDefaults.buttonColors(containerColor = MaterialTheme.colorScheme.secondary),
                            onClick = { navController.navigate(route = AppScreens.EditHorarioScreen.route + "/${opcion}/${horario.id}") })
                        {   Icon(
                            imageVector = Icons.Default.Create,
                            contentDescription = null,
                            modifier = Modifier.size(ButtonDefaults.IconSize))
                            Text("Editar")
                        }

                        Button(
                            colors = ButtonDefaults.buttonColors(containerColor = MaterialTheme.colorScheme.error),
                            onClick = { openAlertDialog = true}) {
                            Icon(
                                imageVector = Icons.Default.Delete,
                                contentDescription = null,
                                modifier = Modifier.size(ButtonDefaults.IconSize))
                            Text("Eliminar") }

                        DialogDelete(openAlertDialog, horario.id, opcion, { openAlertDialog = false }, { openAlertDialog = false })
                    }
                }

            }
        }
    }

}

@Composable
fun DialogDelete(
    openDialog: Boolean,
    idHorario: Int = 1,
    opcion: String = "",
    onDismissRequest: () -> Unit,
    onConfirmation: () -> Unit){

    if (openDialog){
        AlertDialog(
            icon = {
                Icon(imageVector = Icons.Default.Info,
                    contentDescription = "infoIcon")
            },
            title = {
                Text(text = "¿Está seguro de eliminar este Horario?")
            },
            text = {
                Text(text = "Esta acción no es reversible.")
            },
            onDismissRequest = {
                onDismissRequest() },

            confirmButton = {
                TextButton(
                    onClick = {
                        MockDataService.deleteHorarioById(idHorario, opcion)
                        onConfirmation()
                    }
                ) {
                    Text("Confirmar")
                }
            },
            dismissButton = {
                TextButton(
                    onClick = {
                        onDismissRequest()
                    }
                ) {
                    Text("Cancelar")
                }
            }
        )
    }

}
