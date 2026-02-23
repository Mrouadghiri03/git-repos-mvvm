package com.example.gitreposmvvm.domain.repository


import com.example.gitreposmvvm.domain.model.Repository


interface GithubRepository {
    suspend fun getRepositories(page : Int): List<Repository>
}