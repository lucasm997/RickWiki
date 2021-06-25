package br.com.meneghin.rickwiki.ui

import androidx.paging.PagingSource
import androidx.paging.PagingState
import br.com.meneghin.rickwiki.data.dto.character.Character
import br.com.meneghin.rickwiki.domain.usecase.GetCharactersByPage

class CharacterPagingSource(
    val getCharacters: GetCharactersByPage,
) : PagingSource<Int, Character>() {
    override suspend fun load(
        params: LoadParams<Int>
    ): LoadResult<Int, Character> {
        return try {
            // Start refresh at page 1 if undefined.
            val nextPageNumber = params.key ?: 1
            val response = getCharacters(nextPageNumber)
            LoadResult.Page(
                data = response,
                prevKey = null, // Only paging forward.
                nextKey = nextPageNumber+1
            )
        } catch (e: Exception) {
            LoadResult.Error(e)
        }
    }

    override fun getRefreshKey(state: PagingState<Int, Character>): Int? {
        // Try to find the page key of the closest page to anchorPosition, from
        // either the prevKey or the nextKey, but you need to handle nullability
        // here:
        //  * prevKey == null -> anchorPage is the first page.
        //  * nextKey == null -> anchorPage is the last page.
        //  * both prevKey and nextKey null -> anchorPage is the initial page, so
        //    just return null.
        return state.anchorPosition?.let { anchorPosition ->
            val anchorPage = state.closestPageToPosition(anchorPosition)
            anchorPage?.prevKey?.plus(1) ?: anchorPage?.nextKey?.minus(1)
        }
    }
}
