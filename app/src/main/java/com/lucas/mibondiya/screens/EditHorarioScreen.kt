package com.lucas.mibondiya.screens

import android.R
import android.util.Log
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
import androidx.compose.material3.CircularProgressIndicator
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
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
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
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.lucas.mibondiya.controller.getHorarioById
import com.lucas.mibondiya.controller.guardarHorarioEditado
import com.lucas.mibondiya.controller.horaConvertida
import com.lucas.mibondiya.data.model.Horario
import com.lucas.mibondiya.data.model.HorarioCompleto
import com.lucas.mibondiya.data.model.HorarioMock
import com.lucas.mibondiya.navigation.AppScreens
import com.lucas.mibondiya.viewModel.HorarioViewModel
import com.lucas.mibondiya.viewModel.ReferenciasViewModel
import kotlinx.coroutines.flow.firstOrNull
import kotlinx.coroutines.flow.forEach

@Composable
@OptIn(ExperimentalMaterial3Api::class)
fun EditHorarioScreen(navController: NavController, sentido: String = "", idHorario: Int = 1){

    val horario = remember { mutableStateOf<Horario?>(null) }

    val viewModelHorario : HorarioViewModel = hiltViewModel()
    // Cargar el horario cuando se entra a la pantalla
    LaunchedEffect(idHorario) {
        viewModelHorario.getHorarioById(idHorario) { resultado ->
            horario.value = resultado
        }
    }

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

            ContenidoPrincipal(innerPadding, horario.value, sentido, viewModelHorario)},
        )



}

@Composable
fun ContenidoPrincipal(innerPadding: PaddingValues, horario: Horario?, sentido: String = "", viewModel: HorarioViewModel){
    CardEditHorario(horario = horario, sentido =  sentido, viewModel = viewModel, padding = innerPadding)

}

// CREAR VALIDACIÓN DE QUE EL HORARIO NO SEA NULL!!!

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CardEditHorario(modifier: Modifier = Modifier, horario: Horario?, sentido:String = "", viewModel: HorarioViewModel, padding: PaddingValues){
    val radioOptions = listOf<String>("Jesús Maria a Córdoba", "Córdoba a Jesús María")
    val frecuenciaOptions = listOf<String>("Diario (Lun a Dom)", "Lun A Vie")

    val viewModelRef : ReferenciasViewModel = hiltViewModel()

    val listaEmpresas by viewModelRef.empresas.collectAsState()

    // NO CARGA HASTA QUE NO ESTÉN LOS DATOS TRAIDOS DE LA DB
    if ((horario == null) or listaEmpresas.isEmpty()) {
        CircularProgressIndicator()
        return
    }

    val empresaInicial = listaEmpresas.find { it.id == (horario?.empresa_id ?: -1) }
    var empresaSeleccionada by remember { mutableStateOf(empresaInicial) }

    var frecSeleccionada by remember { mutableStateOf(frecuenciaOptions[0]) }
    var seAnuncia by remember { mutableStateOf(horario?.seAnuncia ?: "") }
    var notas by remember { mutableStateOf(horario?.notas ?: "") }
    val (selectedOption, onOptionSelected) = remember { mutableStateOf(radioOptions[0]) }

    val idHorario = horario?.id ?: 99
    val ciudadInicio = horario?.ciudad_inicio_id ?: 1
    val ciudadFin = horario?.ciudad_fin_id ?: 1

    val horaSalidaInicial = horario?.horaSalida?.replace(":", "") ?: "0000"
    var horaSalida by remember { mutableStateOf(horaSalidaInicial) }

    val horaLlegadaInicial = horario?.horaLlegada?.replace(":", "") ?: "0000"
    var horaLlegada by remember { mutableStateOf(horaLlegadaInicial) }


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
                value = ciudadInicio.toString(),
                onValueChange = {}, // obligatorio aunque esté deshabilitado
                label = { Text("Ciudad de Salida") },
                enabled = false,
                readOnly = true
            )

            OutlinedTextField(
                modifier = Modifier.fillMaxWidth(),
                value = ciudadFin.toString(),
                onValueChange = {}, // obligatorio aunque esté deshabilitado
                label = { Text("Ciudad de Llegada") },
                enabled = false,
                readOnly = true
            )


            Spacer(modifier = Modifier.height(16.dp))

            DropdownSelectorEmpresa(listaEmpresas,
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
            InputText(seAnuncia,"El horario se anuncia a", onTextChange = {seAnuncia = it})
            Spacer(modifier = Modifier.height(16.dp))
            InputText(notas,"Notas extras... (Opcional)", onTextChange = {notas = it})
            Spacer(modifier = Modifier.height(26.dp))

            Button(onClick = {viewModel.editarHorario(
                idHorario,
                empresaSeleccionada?.id ?: 1,
                horaConvertida(horaSalida),
                horaConvertida(horaLlegada),
                seAnuncia,
                notas)
            {
                   modificado ->
                if (modificado) {
                    Log.d("MODIFICACION_HORARIO", "Modificado con éxito")
                    seGuardoHorario = true
                } else {
                    Log.d("MODIFICACION_HORARIO", "No se modificó")
                }
            }
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
