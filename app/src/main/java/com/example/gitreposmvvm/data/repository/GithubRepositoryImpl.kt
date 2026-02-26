package com.example.gitreposmvvm.data.repository

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.example.gitreposmvvm.data.paging.GithubPagingSource
import com.example.gitreposmvvm.data.remote.ApiService
import com.example.gitreposmvvm.domain.model.Repository
import com.example.gitreposmvvm.domain.repository.GithubRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GithubRepositoryImpl @Inject constructor(
    private val apiService: ApiService
) : GithubRepository {

    override fun getRepositories(): Flow<PagingData<Repository>> {

        return Pager(
            config = PagingConfig(
                pageSize = 30,
                enablePlaceholders = false
            ),
            pagingSourceFactory = {
                GithubPagingSource(apiService)
            }
        ).flow
    }
}