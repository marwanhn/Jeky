package id.aej.jeky.presentation.screen.register

import id.aej.jeky.core.domain.model.User

sealed class RegisterUiState {

  class Success(val data: User): RegisterUiState()

  class Error(val message: String): RegisterUiState()

  object Idle: RegisterUiState()

  object Loading: RegisterUiState()

}
