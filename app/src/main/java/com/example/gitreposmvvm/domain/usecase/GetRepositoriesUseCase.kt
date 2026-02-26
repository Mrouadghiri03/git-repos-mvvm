package com.example.gitreposmvvm.domain.usecase

import androidx.paging.PagingData
import com.example.gitreposmvvm.domain.model.Repository
import com.example.gitreposmvvm.domain.repository.GithubRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetRepositoriesUseCase @Inject constructor(
    private val repository: GithubRepository
) {
    operator fun invoke(): Flow<PagingData<Repository>> {
        return repository.getRepositories()
    }
}