package br.com.meneghin.rickwiki.domain.usecase

import br.com.meneghin.rickwiki.domain.CoroutineContextProvider
import br.com.meneghin.rickwiki.data.dto.character.Character
import br.com.meneghin.rickwiki.domain.interfaces.CharacterRepository
import kotlinx.coroutines.withContext

class GetCharactersByPage(
    private val coroutineContext: CoroutineContextProvider,
    private val repository: CharacterRepository
) {
    suspend operator fun invoke(page: Int): List<Character> = withContext(coroutineContext.IO) {
        repository.getCharactersByPage(page)
    }
}