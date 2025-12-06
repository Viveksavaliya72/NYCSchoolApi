package com.example.schoolproj.viewModel

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.schoolproj.repositories.SchoolRepository
import com.example.schoolproj.repositories.SchoolWithSat
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SchoolViewModel @Inject constructor(private val repo: SchoolRepository) : ViewModel() {

    private val _uiState = MutableStateFlow<UiState>(UiState.Loading)

    val uiState : StateFlow<UiState> = _uiState

    init {
        loadSchools()
    }

    fun loadSchools(){
        viewModelScope.launch {
            _uiState.value = UiState.Loading;
            try {
                val items: List<SchoolWithSat> = repo.getSchoolsWithSat()
                Log.i("LIST**",items.size.toString())
                _uiState.value = UiState.Success(items)

            }catch (t: Throwable){
                _uiState.value = UiState.Error(t.message?:"Unknown Error")
            }
        }
    }




    sealed class UiState{
        object Loading: UiState()
        data class Success(val schools : List<SchoolWithSat> ): UiState()
        data class Error(val message:String): UiState()
    }
}