package br.com.meneghin.rickwiki.domain.usecase

import br.com.meneghin.rickwiki.domain.CoroutineContextProvider
import br.com.meneghin.rickwiki.domain.interfaces.LocationRepository

class GetLocationById(
    private val coroutineContext: CoroutineContextProvider,
    private val repository: LocationRepository
) {
//    suspend operator fun invoke(id: Int): Location = withContext(coroutineContext) {
//        repository.getLocation(id)
//    }
}