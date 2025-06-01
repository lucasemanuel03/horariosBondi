package com.lucas.mibondiya.service
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.snapshots.SnapshotStateList
import com.lucas.mibondiya.R

data class Empresa(
    val id: Int,
    val nombre: String,
    val idImage: Int,
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
    val ciudadFin: Ciudad,
    val seAnuncia: String = ciudadFin.nombre,
    val notas: String = "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Nullam pretium blandit felis. Mauris viverra sed est eu lacinia volutpat."
)

object MockDataService {

    //EMPRESAS
    val fb = Empresa(1, "Fono Bus", R.drawable.fonobus_te_lleva )
    val fam = Empresa(2, "Grupo FAM", R.drawable.grupo_fam)
    val none = Empresa(2, "NO EMPRESA", R.drawable.grupo_fam)
    val empresas = listOf<Empresa>(fb, fam)

    //Ciudad("Rosario", Lat: -32.9587,Long: -60.6931)
    val cba =  Ciudad(1, "Córdoba", -32.9587, -60.6931)
    val jma =  Ciudad(2, "Jesús María", -32.9587, -60.6931)

    private val arrayHorariosToCba = mutableStateListOf<Horario>(
        Horario("05:15", "06:45", fb, jma, cba),
        Horario("05:40", "06:50", fb, jma, cba),
        Horario("06:00", "07:10", fb, jma, cba),
        Horario("06:15", "07:25", fb, jma, cba),
        Horario("06:30", "08:00", fb, jma, cba),
        Horario("06:40", "07:50", fb, jma, cba),
        Horario("06:45", "07:55", fb, jma, cba),
        Horario("07:10", "08:20", fb, jma, cba),
        Horario("07:25", "08:35", fb, jma, cba),
        Horario("07:30", "09:15", fb, jma, cba),
        Horario("08:00", "09:10", fb, jma, cba),
        Horario("08:10", "09:20", fb, jma, cba),
        Horario("08:30", "10:00", fb, jma, cba),
        Horario("08:35", "09:35", fb, jma, cba),
        Horario("08:41", "09:51", fb, jma, cba),
        Horario("09:15", "10:45", fb, jma, cba),
        Horario("09:30", "11:00", fb, jma, cba),
        Horario("10:00", "11:10", fb, jma, cba),
        Horario("10:15", "11:25", fb, jma, cba),
        Horario("10:30", "12:00", fb, jma, cba),
        Horario("10:45", "11:55", fb, jma, cba),
        Horario("11:30", "13:00", fb, jma, cba),
        Horario("11:35", "13:05", fb, jma, cba),
        Horario("11:45", "12:55", fb, jma, cba),
        Horario("12:05", "13:15", fb, jma, cba),
        Horario("12:30", "14:00", fb, jma, cba),
        Horario("13:00", "14:10", fb, jma, cba),
        Horario("13:30", "15:00", fb, jma, cba),
        Horario("14:10", "15:20", fb, jma, cba),
        Horario("14:20", "15:30", fb, jma, cba),
        Horario("14:30", "16:00", fb, jma, cba),
        Horario("14:45", "15:55", fb, jma, cba),
        Horario("15:00", "16:10", fb, jma, cba),
        Horario("15:30", "17:00", fb, jma, cba),
        Horario("16:15", "17:25", fb, jma, cba),
        Horario("16:30", "18:00", fb, jma, cba),
        Horario("16:55", "18:05", fb, jma, cba),
        Horario("17:25", "18:35", fb, jma, cba),
        Horario("17:30", "19:00", fb, jma, cba),
        Horario("17:41", "18:51", fb, jma, cba),
        Horario("18:00", "19:10", fb, jma, cba),
        Horario("18:30", "20:00", fb, jma, cba),
        Horario("18:45", "19:55", fb, jma, cba),
        Horario("18:50", "20:00", fb, jma, cba),
        Horario("19:20", "20:50", fb, jma, cba),
        Horario("19:30", "21:00", fb, jma, cba),
        Horario("19:50", "21:20", fb, jma, cba),
        Horario("20:20", "21:30", fb, jma, cba),
        Horario("20:30", "22:00", fb, jma, cba),
        Horario("20:45", "21:55", fb, jma, cba),
        Horario("21:15", "22:30", fb, jma, cba),
        Horario("22:20", "23:30", fb, jma, cba),
        Horario("23:00", "00:10", fb, jma, cba)
    )


    private val arrayHorariosToJM = mutableStateListOf<Horario>(
    Horario("05:15", "06:25", fb, cba, jma, ""),
    Horario("05:20", "06:30", fb, cba, jma, ""),
    Horario("06:00", "07:10", fb, cba, jma, ""),
    Horario("06:10", "07:20", fb, cba, jma, ""),
    Horario("06:40", "07:50", fb, cba, jma, ""),
    Horario("07:00", "08:30", fb, cba, jma, ""),
    Horario("07:20", "08:30", fb, cba, jma, ""),
    Horario("07:30", "08:40", fb, cba, jma, ""),
    Horario("08:30", "09:40", fb, cba, jma, ""),
    Horario("09:00", "10:10", fb, cba, jma, ""),
    Horario("09:30", "10:40", fb, cba, jma, ""),
    Horario("10:15", "11:25", fb, cba, jma, ""),
    Horario("10:30", "11:40", fb, cba, jma, ""),
    Horario("11:15", "12:25", fb, cba, jma, ""),
    Horario("11:30", "12:40", fb, cba, jma, ""),
    Horario("11:40", "12:50", fb, cba, jma, ""),
    Horario("11:50", "13:00", fb, cba, jma, ""),
    Horario("12:00", "13:10", fb, cba, jma, ""),
    Horario("12:30", "13:40", fb, cba, jma, ""),
    Horario("12:50", "14:00", fb, cba, jma, ""),
    Horario("13:00", "14:10", fb, cba, jma, ""),
    Horario("14:00", "15:10", fb, cba, jma, ""),
    Horario("14:45", "15:55", fb, cba, jma, ""),
    Horario("15:00", "16:10", fb, cba, jma, ""),
    Horario("15:50", "17:00", fb, cba, jma, ""),
    Horario("16:20", "17:30", fb, cba, jma, ""),
    Horario("16:40", "17:50", fb, cba, jma, ""),
    Horario("17:30", "18:40", fb, cba, jma, ""),
    Horario("17:40", "18:50", fb, cba, jma, ""),
    Horario("18:30", "20:00", fb, cba, jma, ""),
    Horario("18:45", "19:55", fb, cba, jma, ""),
    Horario("19:15", "20:25", fb, cba, jma, ""),
    Horario("19:30", "20:40", fb, cba, jma, ""),
    Horario("19:40", "21:10", fb, cba, jma, ""),
    Horario("20:00", "21:10", fb, cba, jma, ""),
    Horario("20:30", "21:40", fb, cba, jma, ""),
    Horario("21:00", "22:10", fb, cba, jma, ""),
    Horario("22:00", "23:10", fb, cba, jma, ""),
    Horario("23:00", "00:10", fb, cba, jma, ""),

    //HORARIOS FAM
    Horario("05:30", "06:44", fam, cba, jma, "Rio Seco"),
    Horario("07:15", "08:29", fam, cba, jma, "Rio Seco"),
    Horario("08:15", "09:25", fam, cba, jma, "Lucio V. Mansilla"),
    Horario("08:45", "09:59", fam, cba, jma, "Rio Seco"),
    Horario("09:30", "10:40", fam, cba, jma, "Lucio V. Mansilla"),
    Horario("12:45", "14:06", fam, cba, jma, "Villa Quilino"),
    Horario("13:30", "14:55", fam, cba, jma, "Villa Candelaria"),
    Horario("14:30", "15:51", fam, cba, jma, "Villa Quilino"),
    Horario("16:00", "17:14", fam, cba, jma, "Rio Seco"),
    Horario("17:15", "18:29", fam, cba, jma, "Rio Seco"),
    Horario("18:00", "19:21", fam, cba, jma, "Villa Quilino"),
    Horario("19:00", "20:14", fam, cba, jma, "Rio Seco"),
    Horario("22:30", "23:51", fam, cba, jma, "Villa Quilino")
    )


    fun getHorariosToJM(): SnapshotStateList<Horario>{
        return arrayHorariosToJM
    }

    fun getHorariosToCba(): SnapshotStateList<Horario>{
        return arrayHorariosToCba
    }

    fun addHorarioToCba(horario: Horario): Horario{
        arrayHorariosToCba.add(horario)
        return horario
    }

    fun addHorarioToJM(horario: Horario): Horario{
        arrayHorariosToJM.add(horario)
        return horario
    }

    fun crearHorario(sentido: String,
                     empresaId: String,
                     horaSalida: String,
                     horaLlegada: String,
                     seAnuncia: String,
                     notas: String): Horario{

        var origen: Ciudad
        var destino: Ciudad
        val empresa: Empresa? = empresas.firstOrNull { it.nombre == empresaId}

        if (sentido == "Jesús Maria a Córdoba"){
            origen = jma
            destino = cba
    }
        else{
            origen = cba
            destino = jma
        }
        if(empresa != null){

            val horario = Horario(horaSalida, horaLlegada, empresa, origen, destino, seAnuncia, notas)
            return horario
        }
        return Horario(horaSalida, horaLlegada, this.none, origen, destino, seAnuncia, notas)
    }

    fun getAllEmpresas(): List<Empresa>{
        return empresas
    }

    fun getEmpresa(id: Int): Empresa{
        return this.empresas[id]
    }
    fun getAllNombreEmpresas(): List<String>{
        val nombreEmpresas = mutableListOf<String>()
        for (empresa in empresas){
            nombreEmpresas.add(empresa.nombre)
        }
        return nombreEmpresas
    }
}

