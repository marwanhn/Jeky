package id.aej.jeky.core.domain.repository

import id.aej.jeky.core.data.source.Resource
import id.aej.jeky.core.domain.model.User
import kotlinx.coroutines.flow.Flow


interface AuthRepository {
  suspend fun login(email: String, password: String): Flow<Resource<User>>
  suspend fun register(user: User): Flow<Resource<User>>
}