package id.aej.jeky.core.data.interactors

import id.aej.jeky.core.data.source.Resource
import id.aej.jeky.core.data.source.remote.dto.response.GetPlacesRoutesResponse
import id.aej.jeky.core.data.source.remote.dto.response.PlacesResponse
import id.aej.jeky.core.domain.repository.PlacesRepository
import id.aej.jeky.core.domain.usecase.PlacesUseCase
import kotlinx.coroutines.flow.Flow

class PlacesInteractor constructor(
  private val repository: PlacesRepository
): PlacesUseCase {
  override suspend fun getPlaces(keyword: String): Flow<Resource<PlacesResponse>> {
    return repository.getPlaces(keyword)
  }

  override suspend fun getPlaceRoutes(
    origin: String, destination: String
  ): Flow<Resource<GetPlacesRoutesResponse>> {
    return repository.getPlacesRoute(origin, destination)
  }
}