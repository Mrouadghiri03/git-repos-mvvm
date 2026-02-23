package com.example.gitreposmvvm.data.repository

import com.example.gitreposmvvm.data.remote.ApiService
import com.example.gitreposmvvm.domain.model.Repository
import com.example.gitreposmvvm.domain.repository.GithubRepository
import java.text.SimpleDateFormat
import java.util.*


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

   /* override suspend fun getRepositories(page: Int): List<Repository> {
        val response = apiService.getRepositories(
            "created:>2024-01-01",
            "stars",
            "desc",
            page,
            30
        )
        return response.items.map { it.toDomain() } // convertir API model → Domain model
    }

    */
   override suspend fun getRepositories(page: Int): List<Repository> {
       // 1. Calcul de la date (J - 30)
       val calendar = Calendar.getInstance()
       calendar.add(Calendar.DAY_OF_YEAR, -30)

       // 2. Formatage au format YYYY-MM-DD
       val sdf = SimpleDateFormat("yyyy-MM-dd", Locale.US)
       val formattedDate = sdf.format(calendar.getTime())

       // 3. Appel API avec la date dynamique
       val response = apiService.getRepositories(
           "created:>$formattedDate",
           "stars",
           "desc",
           page,
           30
       )
       return response.items.map { it.toDomain() }
   }
}
