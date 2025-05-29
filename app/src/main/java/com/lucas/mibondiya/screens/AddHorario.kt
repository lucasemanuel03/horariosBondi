package com.lucas.mibondiya.screens

import android.annotation.SuppressLint
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.selection.selectable
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.Done
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExposedDropdownMenuBox
import androidx.compose.material3.ExposedDropdownMenuDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.RadioButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TimeInput
import androidx.compose.material3.TopAppBarDefaults.topAppBarColors
import androidx.compose.material3.rememberTimePickerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.semantics.Role
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.lucas.mibondiya.navigation.AppScreens
import java.util.Calendar

@OptIn(ExperimentalMaterial3Api::class)
@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun AddHorario(navController: NavController){
    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(
                title = { Text("Horarios Buses") },
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
            ContenidoPrincipalHorarios(modifier = Modifier, padding = innerPadding)},
    )
}


@Composable
fun ContenidoPrincipalHorarios(modifier: Modifier, padding: PaddingValues ){
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(padding)
            .verticalScroll(rememberScrollState()),
        horizontalAlignment = Alignment.CenterHorizontally,

        ) {
        Text(
            modifier = Modifier.padding(16.dp),
            style = MaterialTheme.typography.titleLarge,
            fontWeight = FontWeight.Bold,
            text = "AGREGAR NUEVO HORARIO",
        )

        CardAddHorario()
    }
}


@Composable
fun CardAddHorario(modifier: Modifier = Modifier){
    val radioOptions = listOf<String>("Jesús Maria a Córdoba", "Córdoba a Jesús María")
    val frecuenciaOptions = listOf<String>("Diario (Lun a Dom)", "Lun A Vie")
    val empresasOptions = listOf<String>("Grupo FAM", "Fono Bus")
    var horaSalida by remember { mutableStateOf("00:00") }
    var horaLlegada by remember { mutableStateOf("00:00") }
    val (selectedOption, onOptionSelected) = remember { mutableStateOf(radioOptions[0]) }


    ElevatedCard(
        elevation = CardDefaults.cardElevation(
            defaultElevation = 12.dp),
        modifier = Modifier.padding(26.dp)

    ) {
        Column(
            modifier = modifier
                .background(color = MaterialTheme.colorScheme.surface)
                .padding(26.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        )
        {

            // Radio buttons
            Text(
                modifier = modifier.padding(bottom = 6.dp),
                text = "SELECCIONE EL SENTIDO",
            )
            radioOptions.forEach { text ->
                Row(
                    Modifier
                        .fillMaxWidth()
                        .height(36.dp)
                        .selectable(
                            selected = (text == selectedOption),
                            onClick = { onOptionSelected(text) },
                            role = Role.RadioButton
                        )
                        .padding(horizontal = 15.dp),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.Center
                ) {
                    RadioButton(
                        selected = (text == selectedOption),
                        onClick = null // null recommended for accessibility with screen readers
                    )
                    Text(
                        text = text,
                        style = MaterialTheme.typography.bodyLarge,
                        modifier = Modifier.padding(start = 16.dp)
                    )
                }
            }

            Spacer(modifier = Modifier.height(16.dp))

            DropdownSelector(empresasOptions, "Empresa Prestadora")
            Spacer(modifier = Modifier.height(16.dp))
            DropdownSelector(frecuenciaOptions, "Frecuencia")

            Spacer(modifier = Modifier.height(16.dp))
            InputHoraMinuto("Horario de Salida")

            InputHoraMinuto("Horario de Llegada")

            Spacer(modifier = Modifier.height(16.dp))

            Spacer(modifier = Modifier.height(26.dp))
            Button(onClick = {/*Pass*/}) {
                Icon(
                    imageVector = Icons.Default.Done,
                    contentDescription = null,
                    modifier = Modifier.size(ButtonDefaults.IconSize)

                )
                Spacer(Modifier.size(ButtonDefaults.IconSpacing))
                Text("Guardar")
            }
        }
    }
}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DropdownSelector(opciones: List<String> = listOf<String>("opc1", "opc2"),
                     label: String = "Label" ) {
    var opcionSeleccionada by remember { mutableStateOf(opciones[0]) }
    var expandido by remember { mutableStateOf(false) }

    ExposedDropdownMenuBox(
        expanded = expandido,
        onExpandedChange = { expandido = !expandido }
    ) {
        TextField(
            value = opcionSeleccionada,
            onValueChange = {},
            readOnly = true,
            label = { Text(label) },
            trailingIcon = {
                ExposedDropdownMenuDefaults.TrailingIcon(expanded = expandido)
            },
            colors = ExposedDropdownMenuDefaults.textFieldColors(),
            modifier = Modifier
                .menuAnchor()
                .fillMaxWidth()
        )

        ExposedDropdownMenu(
            expanded = expandido,
            onDismissRequest = { expandido = false }
        ) {
            opciones.forEach { opcion ->
                DropdownMenuItem(

                    text = { Text(opcion) },
                    onClick = {
                        opcionSeleccionada = opcion
                        expandido = false
                    }
                )
            }
        }
    }
}

@Composable
fun InputText(label: String = "Default"){
    var text by remember { mutableStateOf(TextFieldValue("")) }
    OutlinedTextField(
        value = text,
        label = { Text(label) },
        onValueChange = {
            text = it
        }
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun InputHoraMinuto(
    label: String = "defaultLabel"
) {
    val currentTime = Calendar.getInstance()

    val timePickerState = rememberTimePickerState(
        initialHour = currentTime.get(Calendar.HOUR_OF_DAY),
        initialMinute = currentTime.get(Calendar.MINUTE),
        is24Hour = true,
    )

    Column(horizontalAlignment = Alignment.CenterHorizontally) {
        Text(
            modifier = Modifier.padding(10.dp),
            text = label,
        )

        TimeInput(
            state = timePickerState,
        )
    }
}


@Preview(showSystemUi = false, showBackground = true)
@Composable
fun DefaultPreview(){
    ContenidoPrincipalHorarios(modifier = Modifier, padding = PaddingValues(0.dp))
}



