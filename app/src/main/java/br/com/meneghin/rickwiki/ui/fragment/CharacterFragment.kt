package br.com.meneghin.rickwiki.ui.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.navArgs
import androidx.transition.TransitionInflater
import br.com.meneghin.rickwiki.R
import br.com.meneghin.rickwiki.databinding.FragmentCharacterDetailsBinding
import br.com.meneghin.rickwiki.ui.viewmodel.CharacterViewModel
import com.squareup.picasso.Picasso
import org.koin.androidx.viewmodel.ext.android.viewModel
import org.koin.core.parameter.parametersOf

class CharacterFragment : Fragment() {
    private val args: CharacterFragmentArgs by navArgs()
    lateinit var binding : FragmentCharacterDetailsBinding
    val characterViewModel: CharacterViewModel by viewModel { parametersOf(args.character) }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentCharacterDetailsBinding.inflate(layoutInflater)
        binding.characterName.text = characterViewModel.character.name
        binding.characterGender.text = characterViewModel.character.gender
        binding.characterStatus.text = characterViewModel.character.status
        binding.characterSpecies.text = characterViewModel.character.species
        Picasso.get().load(characterViewModel.character.image).into(binding.characterPhoto)

        context?.let{
            sharedElementEnterTransition = TransitionInflater.from(it).inflateTransition(android.R.transition.move)
        }
        return binding.root
    }

}