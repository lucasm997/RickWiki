package br.com.meneghin.rickwiki.data.dto.character

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Character(
    @SerialName(value = "id")
    val id: Int,
    @SerialName(value = "name")
    val name: String,
    @SerialName(value = "status")
    val status: String,
    @SerialName(value = "species")
    val species: String,
    @SerialName(value = "type")
    val type: String,
    @SerialName(value = "gender")
    val gender: String,
    @SerialName(value = "origin")
    val origin: CharacterLocation,
    @SerialName(value = "location")
    val location: CharacterLocation,
    @SerialName(value = "image")
    val image: String,
    @SerialName(value = "episode")
    val episode: List<String>
)
