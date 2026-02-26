/*package com.example.gitreposmvvm.presentation.ui

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.gitreposmvvm.ui.theme.GitReposMVVMTheme


import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import com.example.gitreposmvvm.databinding.ActivityMainBinding
import com.example.gitreposmvvm.presentation.state.UiState
import com.example.gitreposmvvm.presentation.viewmodel.MainViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private val viewModel: MainViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        viewModel.fetchRepositories("google")

        lifecycleScope.launch {
            viewModel.state.collect { state ->
                when (state) {
                    is UiState.Loading -> {
                        binding.textView.text = "Loading..."
                    }
                    is UiState.Success -> {
                        val repos = state.data as List<*>
                        binding.textView.text =
                            repos.joinToString("\n\n") { it.toString() }
                    }
                    is UiState.Error -> {
                        binding.textView.text = state.message
                    }
                }
            }
        }
    }
}

@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    Text(
        text = "Hello $name!",
        modifier = modifier
    )
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    GitReposMVVMTheme {
        Greeting("Android")
    }
}

 */
/*package com.example.gitreposmvvm.presentation.ui

import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.ProgressBar
import android.widget.TextView
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.gitreposmvvm.R
import com.example.gitreposmvvm.databinding.ActivityMainBinding
import com.example.gitreposmvvm.domain.model.Repository
import com.example.gitreposmvvm.presentation.adapter.RepositoryAdapter
import com.example.gitreposmvvm.presentation.state.UiState
import com.example.gitreposmvvm.presentation.viewmodel.MainViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

// app avec pagination ca marche bien

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private val viewModel: MainViewModel by viewModels()
    private lateinit var recyclerView: RecyclerView
    private lateinit var progressBar: ProgressBar
    private lateinit var tvPage: TextView
    private lateinit var btnNext: Button
    private lateinit var btnPrevious: Button
    private var currentPage = 1

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        recyclerView = findViewById(R.id.recyclerView)
        progressBar = findViewById(R.id.progressBar)
        tvPage = findViewById(R.id.tvPage)
        btnNext = findViewById(R.id.btnNext)
        btnPrevious = findViewById(R.id.btnPrevious)

        recyclerView.layoutManager = LinearLayoutManager(this)

        fetchRepos(currentPage)

        btnNext.setOnClickListener {
            currentPage++
            fetchRepos(currentPage)
        }

        btnPrevious.setOnClickListener {
            if (currentPage > 1) {
                currentPage--
                fetchRepos(currentPage)
            }
        }
    }

    private fun fetchRepos(page: Int) {
        progressBar.visibility = View.VISIBLE
        tvPage.text = "Page $page"

        lifecycleScope.launch {
            viewModel.fetchRepositories(page)
            viewModel.state.collect { state ->
                progressBar.visibility = View.GONE
                when (state) {
                    is UiState.Loading -> progressBar.visibility = View.VISIBLE
                    is UiState.Success -> {
                        recyclerView.adapter = RepositoryAdapter(state.data)
                    }
                    is UiState.Error -> tvPage.text = state.message
                }
            }
        }
    }
}


 */

package com.example.gitreposmvvm.presentation.ui

import android.os.Bundle
import android.view.View
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.gitreposmvvm.databinding.ActivityMainBinding
import com.example.gitreposmvvm.presentation.adapter.RepositoryAdapter
import com.example.gitreposmvvm.presentation.adapter.RepositoryPagingAdapter
import com.example.gitreposmvvm.presentation.state.UiState
import com.example.gitreposmvvm.presentation.viewmodel.MainViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private val viewModel: MainViewModel by viewModels()
    private lateinit var adapter: RepositoryPagingAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        adapter = RepositoryPagingAdapter()

        binding.recyclerView.layoutManager = LinearLayoutManager(this)
        binding.recyclerView.adapter = adapter

        lifecycleScope.launch {
            viewModel.repositories.collect {
                adapter.submitData(it)
            }
        }
    }
}