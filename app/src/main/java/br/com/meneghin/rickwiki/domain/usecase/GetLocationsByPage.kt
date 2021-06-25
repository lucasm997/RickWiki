package br.com.meneghin.rickwiki.domain.usecase

import br.com.meneghin.rickwiki.domain.CoroutineContextProvider
import br.com.meneghin.rickwiki.data.dto.location.Location
import br.com.meneghin.rickwiki.domain.interfaces.LocationRepository
import kotlinx.coroutines.withContext

class GetLocationsByPage(
    private val coroutineContext: CoroutineContextProvider,
    private val repository: LocationRepository
) {
    suspend operator fun invoke(page: Int): List<Location> = withContext(coroutineContext.IO) {
        repository.getLocationsByPage(page)
    }
}