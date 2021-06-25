package br.com.meneghin.rickwiki.data.dto.location

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Location (
    @SerialName(value = "id")
    val id: Int,
    @SerialName(value = "name")
    val name: String,
    @SerialName(value = "type")
    val type: String,
    @SerialName(value = "dimension")
    val dimension: String,
    @SerialName(value = "residents")
    val residents: List<String>,
)