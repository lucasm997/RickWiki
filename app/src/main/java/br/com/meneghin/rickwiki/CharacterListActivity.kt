package br.com.meneghin.rickwiki

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.lifecycleScope
import br.com.meneghin.rickwiki.databinding.ActivityMainBinding
import br.com.meneghin.rickwiki.ui.recyclerview.CharactersAdapter
import br.com.meneghin.rickwiki.ui.viewmodel.CharactersViewModel
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import org.koin.androidx.viewmodel.ext.android.viewModel


class CharacterListActivity : AppCompatActivity() {

    private val characterViewModel: CharactersViewModel by viewModel()

    private val adapter = CharactersAdapter {

    }

    val binding: ActivityMainBinding by lazy {
        DataBindingUtil.setContentView(
            this,
            R.layout.activity_main
        )
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding.charactesRecyclerView.adapter = adapter
        search()
    }

    private var searchJob: Job? = null

    private fun search() {
        // Make sure we cancel the previous job before creating a new one
        searchJob?.cancel()
        searchJob = lifecycleScope.launch {
            characterViewModel.fetchCharacters().collectLatest {
                adapter.submitData(it)
            }
        }
    }



}