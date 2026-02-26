package com.example.gitreposmvvm.data.paging


import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.example.gitreposmvvm.data.remote.ApiService
import com.example.gitreposmvvm.domain.model.Repository
import java.text.SimpleDateFormat
import java.util.*
import javax.inject.Inject


class GithubPagingSource @Inject constructor(
    private val apiService: ApiService
) : PagingSource<Int, Repository>() {

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Repository> {

        val page = params.key ?: 1

        // 1. Obtenir la date d'il y a 30 jours
        val calendar: Calendar = Calendar.getInstance()
        calendar.add(Calendar.DAY_OF_YEAR, -30)


        // 2. Formater la date en "yyyy-MM-dd"
        val sdf: SimpleDateFormat = SimpleDateFormat("yyyy-MM-dd", Locale.US)
        val formattedDate: String = sdf.format(calendar.getTime())


        // 3. Construire la requête GitHub (ex: created:>2026-01-21)
        val query = "created:>$formattedDate"

        return try {

            val response = apiService.getRepositories(
                query,
                "stars",
                "desc",
                page,
                30
            )

            val repos = response.items.map { it.toDomain() }

            LoadResult.Page(
                data = repos,
                prevKey = if (page == 1) null else page - 1,
                nextKey = if (repos.isEmpty()) null else page + 1
            )

        } catch (e: Exception) {
            LoadResult.Error(e)
        }
    }

    override fun getRefreshKey(state: PagingState<Int, Repository>): Int? {
        return state.anchorPosition
    }
}