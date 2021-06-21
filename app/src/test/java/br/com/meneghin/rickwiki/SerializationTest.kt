package br.com.meneghin.rickwiki

import br.com.meneghin.rickwiki.data.ResponseInfo
import br.com.meneghin.rickwiki.data.character.Character
import br.com.meneghin.rickwiki.data.character.CharacterLocation
import br.com.meneghin.rickwiki.data.episode.Episode
import br.com.meneghin.rickwiki.data.location.Location
import kotlinx.serialization.json.Json
import org.junit.Test

class SerializationTest {

    val json = Json { ignoreUnknownKeys = true }

    @Test
    fun `test responseinfo desserialization`() {

        val info = ResponseInfo(
            671,
            1,
            "https://rickandmortyapi.com/api/character?page=2",
            null
        )
        val jsonString = TestUtils.getJson("responseinfo.json")
        val decoded = json.decodeFromString(ResponseInfo.serializer(), jsonString)
        assert(info == decoded)

    }

    @Test
    fun `test character desserialization`() {
        val character = Character(
            1,
            "Rick Sanchez",
            "Alive",
            "Human",
            "",
            "Male",
            CharacterLocation(
                "Earth (C-137)",
                "https://rickandmortyapi.com/api/location/1"
            ),
            CharacterLocation(
                "Earth (Replacement Dimension)",
                "https://rickandmortyapi.com/api/location/20"
            ),
            "https://rickandmortyapi.com/api/character/avatar/1.jpeg",
            listOf("https://rickandmortyapi.com/api/episode/1")
        )
        val jsonString = TestUtils.getJson("character.json")

        val decoded = json.decodeFromString(Character.serializer(), jsonString)
        assert(character == decoded)

    }

    @Test
    fun `test episode desserialization`() {
        val episode = Episode(
            1,
            "Pilot",
            "December 2, 2013",
            "S01E01",
            listOf("https://rickandmortyapi.com/api/character/1")
        )

        val jsonString = TestUtils.getJson("episode.json")

        val decoded = json.decodeFromString(Episode.serializer(), jsonString)
        assert(episode == decoded)
    }

    @Test
    fun `test origin desserialization`() {
        val location = Location(
            1,
            "Earth (C-137)",
            "Planet",
            "Dimension C-137",
            listOf("https://rickandmortyapi.com/api/character/38")
        )

        val jsonString = TestUtils.getJson("location.json")

        val decoded = json.decodeFromString(Location.serializer(), jsonString)
        assert(location == decoded)
    }
}