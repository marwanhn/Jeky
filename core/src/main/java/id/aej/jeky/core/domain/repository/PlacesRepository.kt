package id.aej.jeky.core.domain.repository

import id.aej.jeky.core.data.source.Resource
import id.aej.jeky.core.data.source.remote.dto.response.GetPlacesRoutesResponse
import id.aej.jeky.core.data.source.remote.dto.response.PlacesResponse
import id.aej.jeky.core.domain.model.User
import kotlinx.coroutines.flow.Flow


interface PlacesRepository {
  suspend fun getPlaces(keyword: String): Flow<Resource<PlacesResponse>>
  suspend fun getPlacesRoute(origin: String, destination: String): Flow<Resource<GetPlacesRoutesResponse>>
}