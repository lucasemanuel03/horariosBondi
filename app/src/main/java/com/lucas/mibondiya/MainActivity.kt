package com.lucas.mibondiya

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import com.lucas.mibondiya.navigation.AppNavigation
import com.lucas.mibondiya.ui.theme.MiBondiYaTheme

@OptIn(ExperimentalMaterial3Api::class)
class MainActivity : ComponentActivity() {
    @SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        enableEdgeToEdge()
        setContent {
            MiBondiYaTheme {
                AppNavigation()
            }

        }
    }
}

@Composable
@Preview()
fun DefaultPreview(){
    MiBondiYaTheme(darkTheme = true) {
        AppNavigation()
    }
}


