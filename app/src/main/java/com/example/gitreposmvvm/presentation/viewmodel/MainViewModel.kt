package com.example.gitreposmvvm.presentation.viewmodel

/*
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.gitreposmvvm.domain.usecase.GetRepositoriesUseCase
import com.example.gitreposmvvm.presentation.state.UiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val getRepositoriesUseCase: GetRepositoriesUseCase
) : ViewModel() {

    private val _state = MutableStateFlow<UiState<Any>>(UiState.Loading)
    val state: StateFlow<UiState<Any>> = _state

    fun fetchRepositories(username: String) {
        viewModelScope.launch {
            _state.value = UiState.Loading
            try {
                val repos = getRepositoriesUseCase(username)
                _state.value = UiState.Success(repos)
            } catch (e: Exception) {
                _state.value = UiState.Error(e.message ?: "Unknown error")
            }
        }
    }
}
*/

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.gitreposmvvm.domain.model.Repository
import com.example.gitreposmvvm.domain.usecase.GetRepositoriesUseCase
import com.example.gitreposmvvm.presentation.state.UiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject
import com.example.gitreposmvvm.domain.repository.GithubRepository
/*
@HiltViewModel
class MainViewModel @Inject constructor(
    private val getRepositoriesUseCase: GetRepositoriesUseCase
) : ViewModel() {

    private val _state =
        MutableStateFlow<UiState<List<Repository>>>(UiState.Loading)

    val state: StateFlow<UiState<List<Repository>>> = _state

    /*fun fetchRepositories(username: String) {
        viewModelScope.launch {
            _state.value = UiState.Loading
            try {
                val repos = getRepositoriesUseCase(username)
                _state.value = UiState.Success(repos)
            } catch (e: Exception) {
                _state.value = UiState.Error(e.message ?: "Unknown error")
            }
        }
    }

     */
    fun fetchRepositories(page: Int = 1) {
        viewModelScope.launch {
            val repos = githubRepository.getRepositories(page)
            _state.value = UiState.Success(repos)
        }
    }
}

 */
//app b pagination khedama
@HiltViewModel
class MainViewModel @Inject constructor(
    private val githubRepository: GithubRepository
) : ViewModel() {

    private val _state = MutableStateFlow<UiState<List<Repository>>>(UiState.Loading)
    val state: StateFlow<UiState<List<Repository>>> = _state

    private var currentPage = 1

    // Fonction pour récupérer les repos d’une page donnée
    fun fetchRepositories(page: Int = 1) {
        currentPage = page
        _state.value = UiState.Loading

        viewModelScope.launch {
            try {
                val repos = githubRepository.getRepositories(page)
                _state.value = UiState.Success(repos)
            } catch (e: Exception) {
                _state.value = UiState.Error(e.message ?: "Erreur inconnue")
            }
        }
    }

    // Pour paginer
    fun nextPage() {
        fetchRepositories(currentPage + 1)
    }

    fun previousPage() {
        if (currentPage > 1) {
            fetchRepositories(currentPage - 1)
        }
    }
}


/*@HiltViewModel
class MainViewModel @Inject constructor(
    private val repository: GithubRepository
) : ViewModel() {

    private val _state = MutableStateFlow<UiState<List<Repository>>>(UiState.Loading)
    val state: StateFlow<UiState<List<Repository>>> = _state

    private val currentItems = mutableListOf<Repository>()
    private var currentPage = 1
    private var isLoading = false

    fun fetchNextPage() {
        if (isLoading) return

        viewModelScope.launch {
            isLoading = true
            try {
                val repos = repository.getRepositories(currentPage)
                currentItems.addAll(repos)
                _state.value = UiState.Success(currentItems.toList())
                currentPage++
            } catch (e: Exception) {
                _state.value = UiState.Error(e.message ?: "Erreur inconnue")
            } finally {
                isLoading = false
            }
        }
    }
}

 */