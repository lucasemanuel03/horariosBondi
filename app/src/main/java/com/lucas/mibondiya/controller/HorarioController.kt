package com.lucas.mibondiya.controller

import androidx.lifecycle.viewmodel.compose.viewModel
import com.lucas.mibondiya.data.model.HorarioMock
import com.lucas.mibondiya.service.MockDataService
import com.lucas.mibondiya.viewModel.HorarioViewModel



fun saveAtMockHorario(sentido: String,
                      empresaId: String,
                      horaSalida: String,
                      horaLlegada: String,
                      seAnuncia: String,
                      notas: String): Boolean{

    var horario = MockDataService
        .crearHorario(sentido, empresaId, horaSalida, horaLlegada, seAnuncia, notas)

    if (sentido == "Jesús Maria a Córdoba"){
        MockDataService.addHorarioToCba(horario)
        return true
    } else{
        MockDataService.addHorarioToJM(horario)
        return true
    }
    return false
}

fun guardarHorarioEditado(id: Int,
                          sentido: String,
                          empresaId: String,
                          horaSalida: String,
                          horaLlegada: String,
                          seAnuncia: String,
                          notas: String): Boolean{

    var horaSalidaConv = horaConvertida(horaSalida)
    var horaLlegadaConv = horaConvertida(horaLlegada)

    return MockDataService.editHorario(id, sentido, empresaId, horaSalidaConv, horaLlegadaConv, seAnuncia, notas)
}

fun getHorarioById(id: Int, sentido: String): HorarioMock?{

    return MockDataService.getHorarioById(id, sentido)
}

fun deleteHorarioById(id: Int, sentido: String): Boolean{
    return MockDataService.deleteHorarioById(id, sentido)
}

fun horaConvertida(tiempo: String): String {
    if (tiempo.length != 4 || !tiempo.all { it.isDigit() }) {
        throw IllegalArgumentException("El parámetro debe ser un string de 4 dígitos")
    }
    return tiempo.substring(0, 2) + ":" + tiempo.substring(2, 4)
}
