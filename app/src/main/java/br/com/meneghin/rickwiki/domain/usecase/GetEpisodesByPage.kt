package br.com.meneghin.rickwiki.domain.usecase

import br.com.meneghin.rickwiki.domain.CoroutineContextProvider
import br.com.meneghin.rickwiki.data.dto.episode.Episode
import br.com.meneghin.rickwiki.domain.interfaces.EpisodeRepository
import kotlinx.coroutines.withContext

class GetEpisodesByPage(
    private val coroutineContext: CoroutineContextProvider,
    private val repository: EpisodeRepository
) {
    suspend operator fun invoke(page: Int): List<Episode> = withContext(coroutineContext.IO) {
        repository.getEpisodesByPage(page)
    }
}