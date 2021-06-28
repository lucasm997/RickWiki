package br.com.meneghin.rickwiki.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.*
import br.com.meneghin.rickwiki.data.dto.character.Character
import br.com.meneghin.rickwiki.domain.CoroutineContextProvider
import br.com.meneghin.rickwiki.domain.usecase.GetCharactersByPage
import br.com.meneghin.rickwiki.ui.CharacterPagingSource
import kotlinx.coroutines.flow.Flow


class CharacterViewModel(
    val character : Character
) : ViewModel()
