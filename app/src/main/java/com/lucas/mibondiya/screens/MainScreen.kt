package com.lucas.mibondiya.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.selection.selectable
import androidx.compose.foundation.selection.selectableGroup
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.RadioButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults.topAppBarColors
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.semantics.Role
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.lucas.mibondiya.R
import com.lucas.mibondiya.navigation.AppScreens


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainScreen(navController: NavController){


        Scaffold(
            modifier =
                Modifier
                    .fillMaxSize()
                    .background(color = MaterialTheme.colorScheme.background),
            topBar = {
                CenterAlignedTopAppBar(
                    title = { Text("Horarios Interurbanos") },
                    colors = topAppBarColors(
                        containerColor = MaterialTheme.colorScheme.primaryContainer,
                        titleContentColor = MaterialTheme.colorScheme.onBackground,
                    ),
                    modifier = Modifier.shadow(10.dp),
                )
            },
            content = { innerPadding ->
                ContenidoPrincipal(modifier = Modifier, padding = innerPadding, navController = navController)},

            floatingActionButton = {
                FloatingActionButton(onClick = {
                    navController.navigate(route = AppScreens.AddHorario.route)
                    }
                )

                { Icon(Icons.Default.Add, contentDescription = "Add") }
            },

        )
    }


@Composable
fun ContenidoPrincipal(modifier: Modifier = Modifier, padding: PaddingValues, navController: NavController){
    Box(
        modifier = Modifier
            .fillMaxSize()
    ) {
        // Fondo con imagen
        Image(
            painter = painterResource(id = R.drawable.fondo),
            contentDescription = null,
            contentScale = ContentScale.Crop,
            modifier = Modifier.matchParentSize()
        )

        Column(
            modifier = modifier
                .fillMaxSize()
                .padding(padding)
            ,
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center,

            ) {

            InputDestinoRadio(navController)
        }
    }
}

@Composable
fun InputDestinoRadio(navController: NavController,modifier: Modifier = Modifier){
    val radioOptions = listOf<String>("Jesús Maria a Córdoba", "Córdoba a Jesús María")
    val (selectedOption, onOptionSelected) = remember { mutableStateOf(radioOptions[0]) }
    ElevatedCard(
        modifier = Modifier.padding(26.dp),
        elevation = CardDefaults.cardElevation(
            defaultElevation = 16.dp
        ),
    ) {
        Column(
            modifier
                .selectableGroup()
                .background(color = MaterialTheme.colorScheme.surfaceContainer)
                .padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {

            Text(
                text = "Elige el Destino",

                fontWeight = FontWeight.Bold,
                style = MaterialTheme.typography.titleLarge,
                modifier = Modifier.padding(bottom = 24.dp)
            )

            // Radio buttons
            radioOptions.forEach { text ->
                Row(
                    Modifier
                        .fillMaxWidth()
                        .height(56.dp)
                        .selectable(
                            selected = (text == selectedOption),
                            onClick = { onOptionSelected(text) },
                            role = Role.RadioButton
                        )
                        .padding(horizontal = 16.dp),
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
                        modifier = Modifier.padding(start = 9.dp)
                    )
                }
            }

            Spacer(modifier = Modifier.height(16.dp))

            Button(
                onClick = { navController.navigate(route = AppScreens.HorariosNowScreen.route + "/$selectedOption") }
            ) {
                Icon(
                    imageVector = Icons.Default.Search,
                    contentDescription = null,
                    modifier = Modifier.size(ButtonDefaults.IconSize)

                )
                Spacer(Modifier.size(ButtonDefaults.IconSpacing))
                Text(
                    fontWeight = FontWeight.Bold,
                    color = MaterialTheme.colorScheme.onPrimary,
                    text = "Buscar")
            }
        }
    }
}
