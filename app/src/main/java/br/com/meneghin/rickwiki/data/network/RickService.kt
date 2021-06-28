package br.com.meneghin.rickwiki.data.network

import br.com.meneghin.rickwiki.data.dto.ResponseList
import br.com.meneghin.rickwiki.data.dto.character.Character
import br.com.meneghin.rickwiki.data.dto.episode.Episode
import br.com.meneghin.rickwiki.data.dto.location.Location
import retrofit2.http.GET
import retrofit2.http.Query
import retrofit2.http.Url

interface RickService {

    @GET(CHARACTERS_API)
    suspend fun getCharacter(@Query("page") page: Int? = null): ResponseList<Character>

    @GET(EPISODES_API)
    suspend fun getEpisodes(@Query("page") page: Int? = null): ResponseList<Episode>

    @GET(LOCATIONS_API)
    suspend fun getLocations(@Query("page") page: Int? = null): ResponseList<Location>

    suspend fun <T>getDetailed(@Url url: String) : T

}