package com.lucas.mibondiya.service
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.snapshots.SnapshotStateList
import com.lucas.mibondiya.R
import com.lucas.mibondiya.data.model.Ciudad
import com.lucas.mibondiya.data.model.Empresa
import com.lucas.mibondiya.data.model.HorarioMock


object MockDataService {

    //EMPRESAS
    val fb = Empresa(1, "Fono Bus", R.drawable.fonobus_te_lleva )
    val fam = Empresa(2, "Grupo FAM", R.drawable.grupo_fam)
    val none = Empresa(2, "NO EMPRESA", R.drawable.grupo_fam)
    val empresas = listOf<Empresa>(fb, fam)

    //Ciudad("Rosario", Lat: -32.9587,Long: -60.6931)
    val cba =  Ciudad(1, "Córdoba", -32.9587, -60.6931)
    val jma =  Ciudad(2, "Jesús María", -32.9587, -60.6931)

    private val arrayHorariosToCba = mutableStateListOf<HorarioMock>(
        HorarioMock(1, "05:15", "06:45", fb, jma, cba),
        HorarioMock(2, "05:40", "06:50", fb, jma, cba),
        HorarioMock(3, "06:00", "07:10", fb, jma, cba),
        HorarioMock(4, "06:15", "07:25", fb, jma, cba),
        HorarioMock(5, "06:30", "08:00", fb, jma, cba),
        HorarioMock(6, "06:40", "07:50", fb, jma, cba),
        HorarioMock(7, "06:45", "07:55", fb, jma, cba),
        HorarioMock(8, "07:10", "08:20", fb, jma, cba),
        HorarioMock(9, "07:25", "08:35", fb, jma, cba),
        HorarioMock(10, "07:30", "09:15", fb, jma, cba),
        HorarioMock(11, "08:00", "09:10", fb, jma, cba),
        HorarioMock(12, "08:10", "09:20", fb, jma, cba),
        HorarioMock(13, "08:30", "10:00", fb, jma, cba),
        HorarioMock(14, "08:35", "09:35", fb, jma, cba),
        HorarioMock(15, "08:41", "09:51", fb, jma, cba),
        HorarioMock(16, "09:15", "10:45", fb, jma, cba),
        HorarioMock(17, "09:30", "11:00", fb, jma, cba),
        HorarioMock(18, "10:00", "11:10", fb, jma, cba),
        HorarioMock(19, "10:15", "11:25", fb, jma, cba),
        HorarioMock(20, "10:30", "12:00", fb, jma, cba),
        HorarioMock(21, "10:45", "11:55", fb, jma, cba),
        HorarioMock(22, "11:30", "13:00", fb, jma, cba),
        HorarioMock(23, "11:35", "13:05", fb, jma, cba),
        HorarioMock(24, "11:45", "12:55", fb, jma, cba),
        HorarioMock(25, "12:05", "13:15", fb, jma, cba),
        HorarioMock(26, "12:30", "14:00", fb, jma, cba),
        HorarioMock(27, "13:00", "14:10", fb, jma, cba),
        HorarioMock(28, "13:30", "15:00", fb, jma, cba),
        HorarioMock(29, "14:10", "15:20", fb, jma, cba),
        HorarioMock(30, "14:20", "15:30", fb, jma, cba),
        HorarioMock(31, "14:30", "16:00", fb, jma, cba),
        HorarioMock(32, "14:45", "15:55", fb, jma, cba),
        HorarioMock(33, "15:00", "16:10", fb, jma, cba),
        HorarioMock(34, "15:30", "17:00", fb, jma, cba),
        HorarioMock(35, "16:15", "17:25", fb, jma, cba),
        HorarioMock(36, "16:30", "18:00", fb, jma, cba),
        HorarioMock(37, "16:55", "18:05", fb, jma, cba),
        HorarioMock(38, "17:25", "18:35", fb, jma, cba),
        HorarioMock(39, "17:30", "19:00", fb, jma, cba),
        HorarioMock(40, "17:41", "18:51", fb, jma, cba),
        HorarioMock(41, "18:00", "19:10", fb, jma, cba),
        HorarioMock(42, "18:30", "20:00", fb, jma, cba),
        HorarioMock(43, "18:45", "19:55", fb, jma, cba),
        HorarioMock(44, "18:50", "20:00", fb, jma, cba),
        HorarioMock(45, "19:20", "20:50", fb, jma, cba),
        HorarioMock(46, "19:30", "21:00", fb, jma, cba),
        HorarioMock(47, "19:50", "21:20", fb, jma, cba),
        HorarioMock(48, "20:20", "21:30", fb, jma, cba),
        HorarioMock(49, "20:30", "22:00", fb, jma, cba),
        HorarioMock(50, "20:45", "21:55", fb, jma, cba),
        HorarioMock(51, "21:15", "22:30", fb, jma, cba),
        HorarioMock(52, "22:20", "23:30", fb, jma, cba),
        HorarioMock(53, "23:00", "00:10", fb, jma, cba)
    )

    private val arrayHorariosToJM = mutableStateListOf<HorarioMock>(
        HorarioMock(1, "05:15", "06:25", fb, cba, jma, ""),
        HorarioMock(2, "05:20", "06:30", fb, cba, jma, ""),
        HorarioMock(3, "06:00", "07:10", fb, cba, jma, ""),
        HorarioMock(4, "06:10", "07:20", fb, cba, jma, ""),
        HorarioMock(5, "06:40", "07:50", fb, cba, jma, ""),
        HorarioMock(6, "07:00", "08:30", fb, cba, jma, ""),
        HorarioMock(7, "07:20", "08:30", fb, cba, jma, ""),
        HorarioMock(8, "07:30", "08:40", fb, cba, jma, ""),
        HorarioMock(9, "08:30", "09:40", fb, cba, jma, ""),
        HorarioMock(10, "09:00", "10:10", fb, cba, jma, ""),
        HorarioMock(11, "09:30", "10:40", fb, cba, jma, ""),
        HorarioMock(12, "10:15", "11:25", fb, cba, jma, ""),
        HorarioMock(13, "10:30", "11:40", fb, cba, jma, ""),
        HorarioMock(14, "11:15", "12:25", fb, cba, jma, ""),
        HorarioMock(15, "11:30", "12:40", fb, cba, jma, ""),
        HorarioMock(16, "11:40", "12:50", fb, cba, jma, ""),
        HorarioMock(17, "11:50", "13:00", fb, cba, jma, ""),
        HorarioMock(18, "12:00", "13:10", fb, cba, jma, ""),
        HorarioMock(19, "12:30", "13:40", fb, cba, jma, ""),
        HorarioMock(20, "12:50", "14:00", fb, cba, jma, ""),
        HorarioMock(21, "13:00", "14:10", fb, cba, jma, ""),
        HorarioMock(22, "14:00", "15:10", fb, cba, jma, ""),
        HorarioMock(23, "14:45", "15:55", fb, cba, jma, ""),
        HorarioMock(24, "15:00", "16:10", fb, cba, jma, ""),
        HorarioMock(25, "15:50", "17:00", fb, cba, jma, ""),
        HorarioMock(26, "16:20", "17:30", fb, cba, jma, ""),
        HorarioMock(27, "16:40", "17:50", fb, cba, jma, ""),
        HorarioMock(28, "17:30", "18:40", fb, cba, jma, ""),
        HorarioMock(29, "17:40", "18:50", fb, cba, jma, ""),
        HorarioMock(30, "18:30", "20:00", fb, cba, jma, ""),
        HorarioMock(31, "18:45", "19:55", fb, cba, jma, ""),
        HorarioMock(32, "19:15", "20:25", fb, cba, jma, ""),
        HorarioMock(33, "19:30", "20:40", fb, cba, jma, ""),
        HorarioMock(34, "19:40", "21:10", fb, cba, jma, ""),
        HorarioMock(35, "20:00", "21:10", fb, cba, jma, ""),
        HorarioMock(36, "20:30", "21:40", fb, cba, jma, ""),
        HorarioMock(37, "21:00", "22:10", fb, cba, jma, ""),
        HorarioMock(38, "22:00", "23:10", fb, cba, jma, ""),
        HorarioMock(39, "23:00", "00:10", fb, cba, jma, ""),

        HorarioMock(42, "05:30", "06:44", fam, cba, jma, "Rio Seco"),
        HorarioMock(43, "07:15", "08:29", fam, cba, jma, "Rio Seco"),
        HorarioMock(44, "08:15", "09:25", fam, cba, jma, "Lucio V. Mansilla"),
        HorarioMock(45, "08:45", "09:59", fam, cba, jma, "Rio Seco"),
        HorarioMock(46, "09:30", "10:40", fam, cba, jma, "Lucio V. Mansilla"),
        HorarioMock(47, "12:45", "14:06", fam, cba, jma, "Villa Quilino"),
        HorarioMock(48, "13:30", "14:55", fam, cba, jma, "Villa Candelaria"),
        HorarioMock(49, "14:30", "15:51", fam, cba, jma, "Villa Quilino"),
        HorarioMock(50, "16:00", "17:14", fam, cba, jma, "Rio Seco"),
        HorarioMock(51, "17:15", "18:29", fam, cba, jma, "Rio Seco"),
        HorarioMock(52, "18:00", "19:21", fam, cba, jma, "Villa Quilino"),
        HorarioMock(53, "19:00", "20:14", fam, cba, jma, "Rio Seco"),
        HorarioMock(54, "22:30", "23:51", fam, cba, jma, "Villa Quilino")
    )

    fun getHorariosToJM(): SnapshotStateList<HorarioMock> {
        return arrayHorariosToJM
    }

    fun getHorariosToCba(): SnapshotStateList<HorarioMock> {
        return arrayHorariosToCba
    }

    fun addHorarioToCba(horario: HorarioMock): HorarioMock {
        arrayHorariosToCba.add(horario)
        return horario
    }

    fun addHorarioToJM(horario: HorarioMock): HorarioMock {
        arrayHorariosToJM.add(horario)
        return horario
    }

    fun getUltimoId(horarios: List<HorarioMock>): Int {
        return horarios.last().id
    }

    fun getHorarioById(id: Int, sentido: String): HorarioMock? {
        return if (sentido == "Jesús Maria a Córdoba") {
            arrayHorariosToCba.find { it.id == id }
        } else {
            arrayHorariosToJM.find { it.id == id }
        }
    }

    fun deleteHorarioById(id: Int, sentido: String): Boolean {
        return if (sentido == "Jesús Maria a Córdoba") {
            arrayHorariosToCba.removeIf { it.id == id }
            true
        } else {
            arrayHorariosToJM.removeIf { it.id == id }
            true
        }
    }


    fun editHorario(id: Int,
                    sentido: String,
                    empresaId: String,
                    horaSalida: String,
                    horaLlegada: String,
                    seAnuncia: String,
                    notas: String): Boolean{
        var index: Int
        if (sentido == "Jesús Maria a Córdoba"){
            index = arrayHorariosToCba.indexOfFirst { it.id == id }
            arrayHorariosToCba[index].empresa = getEmpresa(empresaId) ?: fam
            arrayHorariosToCba[index].horaSalida = horaSalida
            arrayHorariosToCba[index].horaLlegada = horaLlegada
            arrayHorariosToCba[index].seAnuncia = seAnuncia
            arrayHorariosToCba[index].notas = notas
            return true
        } else{
            index = arrayHorariosToJM.indexOfFirst { it.id == id }
            arrayHorariosToJM[index].empresa = getEmpresa(empresaId) ?: fam
            arrayHorariosToJM[index].horaSalida = horaSalida
            arrayHorariosToJM[index].horaLlegada = horaLlegada
            arrayHorariosToJM[index].seAnuncia = seAnuncia
            arrayHorariosToJM[index].notas = notas
            return true
        }
        return false
    }

    fun getAllEmpresas(): List<Empresa>{
        return empresas
    }

    fun getEmpresa(id: String): Empresa? {
        return empresas.find { it.nombre == id }
    }

    fun crearHorario(sentido :String, empresaId :String, horaSalida:String, horaLlegada:String, seAnuncia:String, notas:String) : HorarioMock{
       TODO()
        return TODO()
    }

    fun getAllNombreEmpresas(): List<String>{
        val nombreEmpresas = mutableListOf<String>()
        for (empresa in empresas){
            nombreEmpresas.add(empresa.nombre)
        }
        return nombreEmpresas
    }
}

