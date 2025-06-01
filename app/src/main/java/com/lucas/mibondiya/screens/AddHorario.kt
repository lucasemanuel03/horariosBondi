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
import androidx.compose.foundation.text.KeyboardOptions
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
import androidx.compose.material3.TopAppBarDefaults.topAppBarColors
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.semantics.Role
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.OffsetMapping
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.text.input.TransformedText
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.lucas.mibondiya.controller.guardarNuevoHorario
import com.lucas.mibondiya.navigation.AppScreens
import com.lucas.mibondiya.ui.theme.MiBondiYaTheme

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
            ContenidoPrincipalHorarios(padding = innerPadding)},
    )
}


@Composable
fun ContenidoPrincipalHorarios(padding: PaddingValues ){
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
    var empresaSeleccionada by remember { mutableStateOf(empresasOptions[0]) }
    var frecSeleccionada by remember { mutableStateOf(frecuenciaOptions[0]) }
    var horaSalida by remember { mutableStateOf("0000") }
    var horaLlegada by remember { mutableStateOf("0110") }
    var seAnuncia by remember { mutableStateOf("") }
    var notas by remember { mutableStateOf("") }
    val (selectedOption, onOptionSelected) = remember { mutableStateOf(radioOptions[0]) }

    var seGuardoHorario by remember {mutableStateOf(false)}

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
            InputText("El horario se anuncia a", onTextChange = {seAnuncia = it})
            Spacer(modifier = Modifier.height(16.dp))
            InputText("Notas extras... (Opcional)", onTextChange = {notas = it})
            Spacer(modifier = Modifier.height(26.dp))

            Button(onClick = {seGuardoHorario = guardarNuevoHorario(
                selectedOption,
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
                Text(text = "HORARIO CARGADO CORRECTAMENTE!!!")
            }
        }
    }
}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DropdownSelector(opciones: List<String> = listOf<String>("opc1", "opc2"),
                     label: String = "Label",
                     opcionSeleccionada: String,
                     onOptionSelected: (String) -> Unit) {
    var expandido by remember { mutableStateOf(false) }

    ExposedDropdownMenuBox(
        expanded = expandido,
        onExpandedChange = { expandido = !expandido }
    ) {
        OutlinedTextField(
            value = opcionSeleccionada,
            onValueChange = {},
            readOnly = true,
            label = { Text(label) },
            trailingIcon = {
                ExposedDropdownMenuDefaults.TrailingIcon(expanded = expandido)
            },
            colors = ExposedDropdownMenuDefaults.textFieldColors(),
            modifier = Modifier
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
                        onOptionSelected(opcion)
                        expandido = false
                    }
                )
            }
        }
    }
}

@Composable
fun InputText(label: String = "Default",
              onTextChange: (String) -> Unit){
    var text by remember { mutableStateOf(TextFieldValue("")) }
    OutlinedTextField(
        value = text,
        label = { Text(label) },
        onValueChange = {
            text = it
            onTextChange(it.text)
        },
    )
}

@Composable
fun HoraField(
    hora: String,
    label: String = "label",
    @SuppressLint("ModifierParameter") modifier: Modifier = Modifier,
    onHoraChange: (String) -> Unit
) {
    val mask = "00:00"
    val maskNumber = '0'
    var isError by remember { mutableStateOf(false) }

    OutlinedTextField(
        value = hora,
        onValueChange = {
            val soloDigitos = it.filter { it.isDigit() }.take(4)

            //Validar si tiene 4 dígitos
            if (soloDigitos.length == 4) {
                val horas = soloDigitos.substring(0, 2).toIntOrNull()
                val minutos = soloDigitos.substring(2, 4).toIntOrNull()

                //Validar rango: horas 0–23, minutos 0–59
                if (horas != null && minutos != null && horas in 0..23 && minutos in 0..59) {
                    onHoraChange(soloDigitos)
                } else {
                    isError = true
                }
            } else {
                // Aceptar parcial si tiene menos de 4 dígitos
                isError = false // aún no se completa
                onHoraChange(soloDigitos)
            }
        },
        label = { Text(label) },
        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
        visualTransformation = HoraVisualTransformation(mask, maskNumber),
        isError = isError,
        modifier = modifier.fillMaxWidth()
    )

    if (isError) {
        Text(
            text = "Hora inválida",
            color = MaterialTheme.colorScheme.error,
            style = MaterialTheme.typography.labelMedium,
            modifier = Modifier.padding(start = 16.dp, top = 4.dp)
        )
    }
}

class HoraVisualTransformation(
    private val mask: String,
    private val maskNumber: Char
) : VisualTransformation {

    private val maxLength = mask.count { it == maskNumber }

    override fun filter(text: AnnotatedString): TransformedText {
        val trimmed = if (text.length > maxLength) text.take(maxLength) else text

        val annotatedString = buildAnnotatedString {
            if (trimmed.isEmpty()) return@buildAnnotatedString

            var maskIndex = 0
            var textIndex = 0
            while (textIndex < trimmed.length && maskIndex < mask.length) {
                if (mask[maskIndex] != maskNumber) {
                    val nextDigitIndex = mask.indexOf(maskNumber, maskIndex)
                    append(mask.substring(maskIndex, nextDigitIndex))
                    maskIndex = nextDigitIndex
                }
                append(trimmed[textIndex++])
                maskIndex++
            }
        }

        return TransformedText(annotatedString, HoraOffsetMapper(mask, maskNumber))
    }
}

private class HoraOffsetMapper(
    val mask: String,
    val numberChar: Char
) : OffsetMapping {

    override fun originalToTransformed(offset: Int): Int {
        val digitsBefore = offset.coerceAtMost(mask.count { it == numberChar })
        var nonDigitCount = 0
        var countDigits = 0
        var i = 0

        while (i < mask.length && countDigits < digitsBefore) {
            if (mask[i] == numberChar) {
                countDigits++
            } else {
                nonDigitCount++
            }
            i++
        }

        return (digitsBefore + nonDigitCount).coerceAtMost(mask.length)
    }

    override fun transformedToOriginal(offset: Int): Int {
        val limitedOffset = offset.coerceAtMost(mask.length)
        return mask.take(limitedOffset).count { it == numberChar }
    }
}


@Preview(showSystemUi = false, showBackground = true)
@Composable
fun DefaultPreview() {
    MiBondiYaTheme { // 👈 Asegurate de usar tu theme real
        ContenidoPrincipalHorarios(padding = PaddingValues(0.dp))
    }
}



