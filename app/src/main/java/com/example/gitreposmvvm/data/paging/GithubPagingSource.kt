package com.example.gitreposmvvm.data.paging

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.example.gitreposmvvm.data.remote.ApiService
import com.example.gitreposmvvm.domain.model.Repository
import java.text.SimpleDateFormat
import java.util.*

class GithubPagingSource(
    private val apiService: ApiService,
    private val requestedPage: Int
) : PagingSource<Int, Repository>() {

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Repository> {
        // On utilise la page demandée par l'UI
        val page = params.key ?: requestedPage

        return try {
            val calendar = Calendar.getInstance()
            calendar.add(Calendar.DAY_OF_YEAR, -30)
            val date = SimpleDateFormat("yyyy-MM-dd", Locale.US).format(calendar.time)

            val response = apiService.getRepositories(
                "created:>$date", "stars", "desc", page, 30
            )
            val repos = response.items.map { it.toDomain() }

            LoadResult.Page(
                data = repos,
                prevKey = null, // On bloque la pagination auto
                nextKey = null  // On bloque la pagination auto
            )
        } catch (e: Exception) {
            LoadResult.Error(e)
        }
    }

    override fun getRefreshKey(state: PagingState<Int, Repository>): Int? = null
}