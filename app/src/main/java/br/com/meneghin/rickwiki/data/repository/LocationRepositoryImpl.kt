package br.com.meneghin.rickwiki.data.repository

import br.com.meneghin.rickwiki.domain.interfaces.LocationRepository
import br.com.meneghin.rickwiki.data.network.RickService

class LocationRepositoryImpl(private val api: RickService): LocationRepository {

    override suspend fun getLocationsByPage(page: Int) = api.getLocations(page).results
//    override suspend fun getLocations(id: Int): Location  = api.getLocation(id)

}