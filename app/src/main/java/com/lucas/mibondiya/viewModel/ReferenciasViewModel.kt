package com.lucas.mibondiya.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.lucas.mibondiya.data.dao.CiudadDao
import com.lucas.mibondiya.data.dao.EmpresaDao
import com.lucas.mibondiya.data.model.Ciudad
import com.lucas.mibondiya.data.model.Empresa
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ReferenciasViewModel @Inject constructor(
    private val empresaDao: EmpresaDao,
    private val ciudadDao: CiudadDao
) : ViewModel() {

    private val _empresas = MutableStateFlow<List<Empresa>>(emptyList())
    val empresas: StateFlow<List<Empresa>> = _empresas

    private val _ciudades = MutableStateFlow<List<Ciudad>>(emptyList())
    val ciudades: StateFlow<List<Ciudad>> = _ciudades

    //init ==> es una funcion automática que se ejecuta al instanciar la clase
    init {
        viewModelScope.launch {
            empresaDao.getAll().collect { lista ->
                _empresas.value = lista
            }
        }

        viewModelScope.launch {
            ciudadDao.getAll().collect { lista -> _ciudades.value = lista }
        }
    }
}
