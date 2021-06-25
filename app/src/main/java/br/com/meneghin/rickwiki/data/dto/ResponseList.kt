package br.com.meneghin.rickwiki.data.dto

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class ResponseList<T>(
    @SerialName(value = "info")
    val info: ResponseInfo,
    @SerialName(value = "results")
    val results: List<T>
)
