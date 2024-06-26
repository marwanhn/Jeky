package id.aej.jeky.presentation.screen.pick_location

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import id.aej.jeky.JekyApplication
import id.aej.jeky.core.data.source.Resource
import id.aej.jeky.core.data.source.remote.dto.response.toDomain
import id.aej.jeky.core.domain.usecase.PlacesUseCase
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class PickLocationViewModel constructor(
  private val placesUseCase: PlacesUseCase
): ViewModel() {

  private val _uiState = MutableStateFlow<PickLocationUiState>(PickLocationUiState.Idle)
  val uiState: StateFlow<PickLocationUiState> get() = _uiState.asStateFlow()

  private var getPlacesJob: Job? = null

  companion object {
    val Factory: ViewModelProvider.Factory = viewModelFactory {
      initializer {
        val application = (this[ViewModelProvider.AndroidViewModelFactory.APPLICATION_KEY] as JekyApplication)
        PickLocationViewModel(
          application.jekyContainer.placesUseCase
        )
      }
    }
  }

  fun getPlaces(keyword: String) {
    getPlacesJob?.cancel()
    getPlacesJob = viewModelScope.launch {
      _uiState.emit(PickLocationUiState.Loading)
      placesUseCase.getPlaces(keyword)
        .collect {
          when(it) {
            is Resource.Success -> {
              it.data?.let {
                _uiState.emit(PickLocationUiState.Success(it.toDomain()))
              }
            }
            is Resource.Error -> {
              _uiState.emit(PickLocationUiState.Error(it.message))
            }
            else -> Unit
          }
        }
    }
  }

}