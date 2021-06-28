package br.com.meneghin.rickwiki.ui.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.navigation.Navigation
import br.com.meneghin.rickwiki.R
import br.com.meneghin.rickwiki.databinding.FragmentCharacterListBinding
import br.com.meneghin.rickwiki.ui.recyclerview.CharactersAdapter
import br.com.meneghin.rickwiki.ui.viewmodel.CharacterListViewModel
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import org.koin.androidx.viewmodel.ext.android.viewModel


class CharacterListFragment : Fragment() {

    lateinit var binding: FragmentCharacterListBinding
    private val characterViewModel: CharacterListViewModel by viewModel()
    private val adapter = CharactersAdapter {character,extras ->
        val action = CharacterListFragmentDirections.actionOpenDetails(character)

        Navigation.findNavController(
            view ?: throw Exception("Erro ao visualizar personagem. Tente novamente.")
        ).let {
            if (it.currentDestination?.id == R.id.charactersListFragment)
                it.navigate(action, extras)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentCharacterListBinding.inflate(inflater)
        binding.charactesRecyclerView.adapter = adapter
        search()
        return binding.root
    }


    private var searchJob: Job? = null

    private fun search() {

        searchJob?.cancel()
        searchJob = lifecycleScope.launch {
            characterViewModel.fetchCharacters().collectLatest {
                adapter.submitData(it)
            }
        }
    }
}