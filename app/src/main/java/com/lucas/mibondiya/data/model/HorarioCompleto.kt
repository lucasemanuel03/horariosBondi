package com.lucas.mibondiya.data.model


data class HorarioCompleto(
    val id: Int,
    val horaSalida: String,
    val horaLlegada: String,
    val empresaNombre: String,
    val empresaImagenId: Int,
    val ciudadInicioNombre: String,
    val ciudadFinNombre: String,
    val seAnuncia:String,
    val notas: String
)
