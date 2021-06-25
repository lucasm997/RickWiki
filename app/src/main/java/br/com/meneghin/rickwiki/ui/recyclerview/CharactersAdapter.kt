package br.com.meneghin.rickwiki.ui.recyclerview

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import br.com.meneghin.rickwiki.data.dto.character.Character
import br.com.meneghin.rickwiki.databinding.CharacterViewBinding
import com.squareup.picasso.Picasso

class CharactersAdapter(private val onCharacterClick: () -> Unit) :
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
            binding.characterName.setText(character?.name)
            binding.characterGender.setText(character?.gender)
            binding.characterStatus.setText(character?.status)
            binding.characterSpecies.setText(character?.species)
            Picasso.get().load(character?.image).into(binding.characterPhoto);
        }
    }

    override fun onBindViewHolder(holder: CharacterViewHolder, position: Int) {
        holder.bind(position)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CharacterViewHolder =
        CharacterViewHolder(CharacterViewBinding.inflate(LayoutInflater.from(parent.context)))

}