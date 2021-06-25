package br.com.meneghin.rickwiki.domain

import kotlin.coroutines.CoroutineContext

class CoroutineContextProvider(
    val Main: CoroutineContext,
    val IO: CoroutineContext
)