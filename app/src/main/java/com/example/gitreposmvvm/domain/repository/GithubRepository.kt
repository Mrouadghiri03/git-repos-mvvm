package com.example.gitreposmvvm.domain.repository


import com.example.gitreposmvvm.domain.model.Repository



import androidx.paging.PagingData
import kotlinx.coroutines.flow.Flow

interface GithubRepository {
    fun getRepositories(): Flow<PagingData<Repository>>
}