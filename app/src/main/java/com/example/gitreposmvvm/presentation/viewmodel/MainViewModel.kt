package com.example.gitreposmvvm.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.example.gitreposmvvm.domain.model.Repository
import com.example.gitreposmvvm.domain.repository.GithubRepository
import com.example.gitreposmvvm.domain.usecase.GetRepositoriesUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val getRepositoriesUseCase: GetRepositoriesUseCase
) : ViewModel() {

    val repositories =
        getRepositoriesUseCase()
            .cachedIn(viewModelScope)
}