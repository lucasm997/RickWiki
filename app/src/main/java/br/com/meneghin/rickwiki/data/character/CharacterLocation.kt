package br.com.meneghin.rickwiki.data.character

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class CharacterLocation(
    @SerialName(value = "name")
    val name: String,
    @SerialName(value = "url")
    val url: String
)
