package br.com.meneghin.rickwiki.data.repository

import br.com.meneghin.rickwiki.data.dto.location.Location
import br.com.meneghin.rickwiki.data.network.RickService
import br.com.meneghin.rickwiki.domain.interfaces.LocationRepository

class LocationRepositoryImpl(private val api: RickService) : LocationRepository {

    override suspend fun getLocationsByPage(page: Int) = api.getLocations(page).results

    override suspend fun getLocation(url: String): Location? = api.getDetailed(url)

}