package com.example.gitreposmvvm.data.repository

import com.example.gitreposmvvm.data.remote.ApiService
import com.example.gitreposmvvm.domain.model.Repository
import com.example.gitreposmvvm.domain.repository.GithubRepository



import javax.inject.Inject

/*
class GithubRepositoryImpl @Inject constructor(

    private val apiService: ApiService
) : GithubRepository {

    override suspend fun getRepositories(username: String): List<Repository> {
        return apiService.getRepositories(username)
            .map { it.toDomain() }
    }
}

 */
class GithubRepositoryImpl @Inject constructor(
    private val apiService: ApiService
) : GithubRepository {

    override suspend fun getRepositories(page: Int): List<Repository> {
        val response = apiService.getRepositories(
            "created:>2024-01-01",
            "stars",
            "desc",
            page,
            30
        )
        return response.items.map { it.toDomain() } // convertir API model → Domain model
    }
}
