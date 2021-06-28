package br.com.meneghin.rickwiki.domain.interfaces

import br.com.meneghin.rickwiki.data.dto.location.Location

interface LocationRepository {
    suspend fun getLocationsByPage(page: Int) : List<Location>
    suspend fun getLocation(url: String) : Location?
}