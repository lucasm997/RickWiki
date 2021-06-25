package br.com.meneghin.rickwiki.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.*
import br.com.meneghin.rickwiki.data.dto.character.Character
import br.com.meneghin.rickwiki.domain.CoroutineContextProvider
import br.com.meneghin.rickwiki.domain.usecase.GetCharactersByPage
import br.com.meneghin.rickwiki.ui.CharacterPagingSource
import kotlinx.coroutines.flow.Flow


class CharactersViewModel(
    private val contextProvider: CoroutineContextProvider,
    private val getCharacterByPage: GetCharactersByPage,
    private val source: CharacterPagingSource
) : ViewModel() {

    fun fetchCharacters(): Flow<PagingData<Character>> {
        val newResult: Flow<PagingData<Character>> =
            getSearchResultStream().cachedIn(viewModelScope)
        return newResult
    }

    private fun getSearchResultStream(): Flow<PagingData<Character>> {
        return Pager(
            config = PagingConfig(
                pageSize = 20,
                enablePlaceholders = false
            ),
            pagingSourceFactory = { source }
        ).flow
    }


}