package br.com.meneghin.rickwiki.domain.interfaces

import br.com.meneghin.rickwiki.data.dto.character.Character

interface CharacterRepository {
    suspend fun getCharactersByPage(page: Int) : List<Character>
    suspend fun getCharacter(url: String) : Character
}