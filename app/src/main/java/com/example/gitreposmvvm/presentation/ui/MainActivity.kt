package com.example.gitreposmvvm.presentation.ui

import android.os.Bundle
import android.view.View
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import androidx.paging.LoadState
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.gitreposmvvm.databinding.ActivityMainBinding
import com.example.gitreposmvvm.presentation.adapter.RepositoryPagingAdapter
import com.example.gitreposmvvm.presentation.viewmodel.MainViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private val viewModel: MainViewModel by viewModels()
    private val adapter = RepositoryPagingAdapter()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Configuration RecyclerView
        binding.recyclerView.layoutManager = LinearLayoutManager(this)
        binding.recyclerView.adapter = adapter

        // 1. Observer les données de la liste
        lifecycleScope.launch {
            viewModel.repoFlow.collectLatest { pagingData ->
                adapter.submitData(pagingData)
            }
        }

        // 2. Observer le numéro de page pour l'affichage
        lifecycleScope.launch {
            viewModel.currentPage.collect { page ->
                binding.txtCurrentPage.text = "Page: $page"
            }
        }

        // 3. Boutons
        binding.btnNext.setOnClickListener { viewModel.nextPage() }
        binding.btnPrevious.setOnClickListener { viewModel.previousPage() }

        // 4. Gestion des états (ProgressBar et activation boutons)
        adapter.addLoadStateListener { loadState ->
            val isLoading = loadState.refresh is LoadState.Loading
            binding.progressBar.visibility = if (isLoading) View.VISIBLE else View.GONE

            // On désactive les boutons pendant le chargement
            binding.btnNext.isEnabled = !isLoading
            binding.btnPrevious.isEnabled = !isLoading && viewModel.currentPage.value > 1
        }
    }
}