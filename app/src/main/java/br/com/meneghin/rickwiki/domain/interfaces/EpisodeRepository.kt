package br.com.meneghin.rickwiki.domain.interfaces

import br.com.meneghin.rickwiki.data.dto.episode.Episode

interface EpisodeRepository {
    suspend fun getEpisodesByPage(page: Int): List<Episode>
}