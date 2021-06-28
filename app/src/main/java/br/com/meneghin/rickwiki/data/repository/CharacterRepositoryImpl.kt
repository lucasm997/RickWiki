package br.com.meneghin.rickwiki.data.repository

import br.com.meneghin.rickwiki.data.dto.character.Character
import br.com.meneghin.rickwiki.domain.interfaces.CharacterRepository
import br.com.meneghin.rickwiki.data.network.RickService

class CharacterRepositoryImpl(private val api: RickService): CharacterRepository{

    override suspend fun getCharactersByPage(page: Int) = api.getCharacter(page).results

    override suspend fun getCharacter(url: String): Character = api.getDetailed(url)

}