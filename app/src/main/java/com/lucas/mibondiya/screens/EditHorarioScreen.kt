package com.lucas.mibondiya.screens

import android.R
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.selection.selectable
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.Done
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.RadioButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TopAppBarDefaults.topAppBarColors
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.modifier.modifierLocalConsumer
import androidx.compose.ui.semantics.Role
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.lucas.mibondiya.controller.getHorarioById
import com.lucas.mibondiya.controller.guardarHorarioEditado
import com.lucas.mibondiya.controller.guardarNuevoHorario
import com.lucas.mibondiya.navigation.AppScreens
import com.lucas.mibondiya.service.Horario

@Composable
@OptIn(ExperimentalMaterial3Api::class)
fun EditHorarioScreen(navController: NavController, sentido: String = "", idHorario: Int = 1){


    val horario = getHorarioById(idHorario, sentido)


    Scaffold (
        topBar = {
            CenterAlignedTopAppBar(
                title = { Text("Editar Horarios") },
                navigationIcon = {
                    IconButton(onClick = {
                        navController.navigate(route = AppScreens.ShowHorariosForEditScreen.route) }) {
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

            ContenidoPrincipal(innerPadding, horario, sentido)},
        )



}

@Composable
fun ContenidoPrincipal(innerPadding: PaddingValues, horario: Horario?, sentido: String = ""){
    CardEditHorario(horario = horario, sentido =  sentido, padding = innerPadding)

}

// CREAR VALIDACIÓN DE QUE EL HORARIO NO SEA NULL!!!

@Preview
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CardEditHorario(modifier: Modifier = Modifier, horario: Horario?, sentido:String = "", padding: PaddingValues){
    val radioOptions = listOf<String>("Jesús Maria a Córdoba", "Córdoba a Jesús María")
    val frecuenciaOptions = listOf<String>("Diario (Lun a Dom)", "Lun A Vie")
    val empresasOptions = listOf<String>("Grupo FAM", "Fono Bus")
    var empresaSeleccionada by remember { mutableStateOf(horario?.empresa?.nombre ?: "Sin Empresa") }
    var frecSeleccionada by remember { mutableStateOf(frecuenciaOptions[0]) }
    var seAnuncia by remember { mutableStateOf("") }
    var notas by remember { mutableStateOf("") }
    val (selectedOption, onOptionSelected) = remember { mutableStateOf(radioOptions[0]) }

    val idHorario = horario?.id ?: 123
    val ciudadInicio = horario?.ciudadInicio?.nombre ?: "ciudad"
    val ciudadFin = horario?.ciudadFin?.nombre ?: "ciudad"
    var horaSalidaHorario = horario?.horaSalida ?: "00:00"
    horaSalidaHorario = horaSalidaHorario.removeRange(2, 3)

    var horaLlegadaHorario = horario?.horaLlegada ?: "00:00"
    horaLlegadaHorario = horaLlegadaHorario.removeRange(2, 3)


    var horaSalida by remember { mutableStateOf(horaSalidaHorario) }
    var horaLlegada by remember { mutableStateOf(horaLlegadaHorario) }

    val seAnunciaHorario = horario?.seAnuncia ?: "No anuncia"
    val notasHorario = horario?.notas ?: "Sin notas..."

    var seGuardoHorario by remember {mutableStateOf(false)}


    ElevatedCard(
        elevation = CardDefaults.cardElevation(
            defaultElevation = 12.dp),
        modifier = Modifier.padding(padding)

    ) {

        Column(
            modifier = modifier
                .background(color = MaterialTheme.colorScheme.surface)
                .padding(26.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        )
        {
            Text(
                style = MaterialTheme.typography.headlineMedium,
                text = "HORARIO #$idHorario"
            )
            Spacer(modifier = Modifier.height(26.dp))

            OutlinedTextField(
                modifier = Modifier.fillMaxWidth(),
                value = ciudadInicio,
                onValueChange = {}, // obligatorio aunque esté deshabilitado
                label = { Text("Ciudad de Salida") },
                enabled = false,
                readOnly = true
            )

            OutlinedTextField(
                modifier = Modifier.fillMaxWidth(),
                value = ciudadFin,
                onValueChange = {}, // obligatorio aunque esté deshabilitado
                label = { Text("Ciudad de Llegada") },
                enabled = false,
                readOnly = true
            )


            Spacer(modifier = Modifier.height(16.dp))

            DropdownSelector(empresasOptions,
                "Empresa Prestadora",
                opcionSeleccionada = empresaSeleccionada,
                onOptionSelected = {empresaSeleccionada = it})

            Spacer(modifier = Modifier.height(16.dp))

            DropdownSelector(frecuenciaOptions,
                "Frecuencia",
                opcionSeleccionada = frecSeleccionada,
                onOptionSelected = { frecSeleccionada = it })

            Spacer(modifier = Modifier.height(16.dp))
            HoraField(horaSalida, "Hora de Salida", onHoraChange = { horaSalida = it})
            Spacer(modifier = Modifier.height(16.dp))
            HoraField(horaLlegada, "Hora de Llegada", onHoraChange = { horaLlegada = it})
            Spacer(modifier = Modifier.height(16.dp))
            InputText(seAnunciaHorario,"El horario se anuncia a", onTextChange = {seAnuncia = it})
            Spacer(modifier = Modifier.height(16.dp))
            InputText(notasHorario,"Notas extras... (Opcional)", onTextChange = {notas = it})
            Spacer(modifier = Modifier.height(26.dp))

            Button(onClick = {seGuardoHorario = guardarHorarioEditado(
                idHorario,
                sentido,
                empresaSeleccionada,
                horaSalida,
                horaLlegada,
                seAnuncia,
                notas )
            }) {
                Icon(
                    imageVector = Icons.Default.Done,
                    contentDescription = null,
                    modifier = Modifier.size(ButtonDefaults.IconSize)

                )
                Spacer(Modifier.size(ButtonDefaults.IconSpacing))
                Text("Guardar")
            }
            Text(text = "Hora llegada: $horaLlegada")

            if (seGuardoHorario){
                Text(text = "HORARIO EDITADO CORRECTAMENTE!!!")
            }
        }
    }
}
