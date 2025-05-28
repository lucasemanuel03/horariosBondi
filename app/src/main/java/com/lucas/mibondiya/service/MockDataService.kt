package com.lucas.horariosbondi.service

data class Empresa(
    val id: Int,
    val nombre: String,
)

data class Ciudad(
    val id: Int,
    val nombre: String,
    val latitud: Double,
    val longitud: Double,
)

data class Horario(
    val horaSalida: String,
    val horaLlegada: String,
    val empresa: Empresa,
    val ciudadInicio: Ciudad,
    val ciudadFin: Ciudad
)

class MockDataService {

    //EMPRESAS
    val fb = Empresa(1, "Fono Bus")
    val fam = Empresa(2, "Grupo FAM")
    val empresas = listOf<Empresa>(fb, fam)

    //Ciudad("Rosario", Lat: -32.9587,Long: -60.6931)
    val cba =  Ciudad(1, "Córdoba", -32.9587, -60.6931)
    val jma =  Ciudad(2, "Jesús María", -32.9587, -60.6931)

    private val arrayHorariosToCba = listOf<Horario>(
        Horario("07:30", "08:45", fb, jma, cba),
        Horario("09:30", "10:45", fam, jma, cba),
        Horario("11:30", "12:45", fb, jma, cba),
        Horario("14:30", "15:45", fam, jma, cba)
    )

    private val arrayHorariosToJM = listOf<Horario>(
        Horario("08:30", "09:45", fb, cba, jma),
        Horario("09:00", "10:15", fam, cba, jma),
        Horario("11:30", "12:45", fb, cba, jma),
        Horario("15:30", "16:45", fam, cba, jma)
    )

    fun getHorariosToJM(): List<Horario>{
        return arrayHorariosToJM
    }

    fun getHorariosToCba(): List<Horario>{
        return arrayHorariosToCba
    }
    fun getAllEmpresas(): List<Empresa>{
        return empresas
    }
    fun getAllNombreEmpresas(): List<String>{
        val nombreEmpresas = mutableListOf<String>()
        for (empresa in empresas){
            nombreEmpresas.add(empresa.nombre)
        }
        return nombreEmpresas
    }
}

