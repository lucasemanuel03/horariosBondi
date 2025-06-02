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
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.selection.selectable
import androidx.compose.foundation.selection.selectableGroup
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.AddCircle
import androidx.compose.material.icons.filled.Build
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material.icons.filled.Info
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
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
import androidx.compose.material3.TextButton
import androidx.compose.material3.TopAppBarDefaults.topAppBarColors
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.semantics.Role
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.lucas.mibondiya.R
import com.lucas.mibondiya.navigation.AppScreens
import com.lucas.mibondiya.service.MockDataService


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainScreen(navController: NavController){


        Scaffold(
            modifier =
                Modifier
                    .fillMaxSize()
                    ,
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
    val options = listOf<String>("Jesús Maria a Córdoba", "Córdoba a Jesús María")

    Box(
        modifier = Modifier
            .fillMaxSize()

    ) {


        Column(
            modifier = modifier
                .fillMaxSize()
                .padding(padding)
            ,
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center,

            ) {

            ElevatedCardWithImage(
                title = "A Córdoba",
                label = "Ver Horarios",
                onClickFun = {navController.navigate(AppScreens.HorariosNowScreen.route + "/${options[0]}")},
                idImage = R.drawable.cordoba_image,
                backgroundColor = MaterialTheme.colorScheme.surfaceBright)
            Spacer(modifier = Modifier.height(12.dp))
            ElevatedCardWithImage(
                title = "A Jesús María",
                label = "Ver Horarios",
                onClickFun = {navController.navigate(AppScreens.HorariosNowScreen.route + "/${options[1]}")},
                idImage = R.drawable.jesus_maria,
                backgroundColor = MaterialTheme.colorScheme.surfaceBright)

            Spacer(modifier = Modifier.height(26.dp))
            ElevatedCardWithIcon(
                title = "Gestionar Horarios",
                label = "Panel de Gestiones",
                onClickFun = {navController.navigate(AppScreens.ShowHorariosForEditScreen.route)},
                idIcon = Icons.Default.Build,
                backgroundColor = MaterialTheme.colorScheme.secondaryContainer)

            Spacer(modifier = Modifier.height(12.dp))
            ElevatedCardWithIcon(
                title = "Nuevo Horario",
                label = "Panel de Horarios",
                onClickFun = {navController.navigate(AppScreens.AddHorario.route)},
                idIcon = Icons.Default.AddCircle,
                backgroundColor = MaterialTheme.colorScheme.secondaryContainer)
        }

    }
}



@Composable
fun GoToEditHorariosCard(navController: NavController){

    ElevatedCard() {
        Column {
            Text(text = "Editar Horarios")

            Button(
                onClick = { navController.navigate(route = AppScreens.ShowHorariosForEditScreen.route) }
            ) {
                Icon(
                    imageVector = Icons.Default.Edit,
                    contentDescription = null,
                    modifier = Modifier.size(ButtonDefaults.IconSize)

                )
                Spacer(Modifier.size(ButtonDefaults.IconSpacing))
                Text(
                    fontWeight = FontWeight.Bold,
                    color = MaterialTheme.colorScheme.onPrimary,
                    text = "Ir a Edicion")
            }

            Button(
                onClick = { navController.navigate(route = AppScreens.ShowHorariosForEditScreen.route) }
            ) {
                Icon(
                    imageVector = Icons.Default.Edit,
                    contentDescription = null,
                    modifier = Modifier.size(ButtonDefaults.IconSize)

                )
                Spacer(Modifier.size(ButtonDefaults.IconSpacing))
                Text(
                    fontWeight = FontWeight.Bold,
                    color = MaterialTheme.colorScheme.onPrimary,
                    text = "Ir a Edicion")
            }
        }
    }

}

@Preview
@Composable
fun ElevatedCardWithIcon(title: String = "Gestionar Horarios",
                         label: String = "Panel de Gestiones",
                         onClickFun: () -> Unit = {},
                         idIcon: ImageVector = Icons.Default.Info,
                         backgroundColor: Color = MaterialTheme.colorScheme.surfaceContainerLow,
                         colorTitle: Color = MaterialTheme.colorScheme.onSurface
                          )
{

    ElevatedCard(
        onClick = {onClickFun()},

        elevation = CardDefaults.cardElevation(
            defaultElevation = 6.dp
        ),
        modifier = Modifier
            .size(width = 350.dp, height = 80.dp),
        shape = MaterialTheme.shapes.medium,
        colors = CardDefaults.cardColors(containerColor = backgroundColor),

    ) {
        Row(modifier = Modifier.padding(16.dp).fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
            ){

            Column(

                verticalArrangement = Arrangement.Center
            ) {

                Text(
                    style = MaterialTheme.typography.titleSmall,
                    text = label,
                )

                Text(
                    text = title,
                    color = colorTitle,
                    style = MaterialTheme.typography.headlineSmall,


                )
            }
            Spacer(modifier = Modifier.width(16.dp))

            Icon(
                imageVector = idIcon,
                contentDescription = "descr",
                modifier = Modifier.size(40.dp)
            )

        }

    }
}

@Preview
@Composable
fun ElevatedCardWithImage(title: String = "A Córdoba",
                          label: String = "Ver Horarios",
                          onClickFun: () -> Unit = {},
                          idImage: Int = R.drawable.cordoba_image,
                          backgroundColor: Color = Color(0xFFFFFFFF),

)
{

    ElevatedCard(
        onClick = {onClickFun()},
        elevation = CardDefaults.cardElevation(
            defaultElevation = 6.dp
        ),
        modifier = Modifier
            .size(width = 350.dp, height = 90.dp),
        shape = MaterialTheme.shapes.medium,
        colors = CardDefaults.cardColors(containerColor = backgroundColor),

        ) {
        Row(modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ){

            Column(
                modifier = Modifier.padding(16.dp),
                verticalArrangement = Arrangement.Center
            ) {

                Text(
                    style = MaterialTheme.typography.titleSmall,
                    text = label,
                )

                Text(
                    text = title,
                    style = MaterialTheme.typography.headlineMedium,
                    fontWeight = FontWeight.Bold,

                    )
            }
            Spacer(modifier = Modifier.width(16.dp))

            Image(
                painter = painterResource(id = idImage),
                contentDescription = "Descriptive text for the image",
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .height(100.dp)
                    .width(120.dp)
            )

        }

    }
}


