package com.lucas.mibondiya

import android.app.Application
import com.lucas.mibondiya.service.DatabasePreloader
import dagger.hilt.android.HiltAndroidApp
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltAndroidApp
class Application : Application(), CoroutineScope by CoroutineScope(Dispatchers.Default) {

    @Inject
    lateinit var databasePreloader: DatabasePreloader

    override fun onCreate() {
        super.onCreate()
        // Ahora lanzamos la precarga en background
        launch {
            databasePreloader.preloadDataIfNeeded()
        }
    }
}
