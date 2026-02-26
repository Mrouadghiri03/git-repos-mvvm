package com.example.gitreposmvvm.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.example.gitreposmvvm.domain.model.Repository
import com.example.gitreposmvvm.domain.repository.GithubRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.flatMapLatest
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val repository: GithubRepository
) : ViewModel() {

    // On stocke la page actuelle dans un StateFlow
    private val _currentPage = MutableStateFlow(1)
    val currentPage: StateFlow<Int> = _currentPage

    // Dès que _currentPage change, flatMapLatest relance la requête au repository
    @OptIn(ExperimentalCoroutinesApi::class)
    val repoFlow = _currentPage.flatMapLatest { page ->
        repository.getRepositories(page)
    }.cachedIn(viewModelScope)

    fun nextPage() {
        _currentPage.value++
    }

    fun previousPage() {
        if (_currentPage.value > 1) {
            _currentPage.value--
        }
    }
}