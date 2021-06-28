package br.com.meneghin.rickwiki.ui.recyclerview

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.navigation.Navigator
import androidx.navigation.fragment.FragmentNavigator
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import br.com.meneghin.rickwiki.data.dto.character.Character
import br.com.meneghin.rickwiki.databinding.CharacterViewBinding
import com.squareup.picasso.Picasso

class CharactersAdapter(private val onCharacterClick: (Character, Navigator.Extras) -> Unit) :
    PagingDataAdapter<Character, CharactersAdapter.CharacterViewHolder>(CharacterDiff) {

    object CharacterDiff : DiffUtil.ItemCallback<Character>() {
        override fun areItemsTheSame(oldItem: Character, newItem: Character): Boolean =
            newItem.id == oldItem.id

        override fun areContentsTheSame(oldItem: Character, newItem: Character): Boolean =
            newItem == oldItem
    }

    inner class CharacterViewHolder(val binding: CharacterViewBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(position: Int) {
            val character = getItem(position)
            binding.characterName.text = character?.name
            binding.characterGender.text = character?.gender
            binding.characterStatus.text = character?.status
            binding.characterSpecies.text = character?.species
            binding.root.setOnClickListener {
                val extras = FragmentNavigator.Extras.Builder()
                extras.addSharedElement(binding.characterPhoto, "avatar")
                character?.let{
                    onCharacterClick(character,extras.build())

                }
            }
            Picasso.get().load(character?.image).into(binding.characterPhoto)
        }
    }

    override fun onBindViewHolder(holder: CharacterViewHolder, position: Int) {
        holder.bind(position)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CharacterViewHolder =
        CharacterViewHolder(CharacterViewBinding.inflate(LayoutInflater.from(parent.context)))

}