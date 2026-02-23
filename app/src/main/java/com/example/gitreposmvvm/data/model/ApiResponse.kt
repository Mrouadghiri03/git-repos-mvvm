package com.example.gitreposmvvm.data.model

//import com.example.gitreposmvvm.domain.model.Repository
import com.example.gitreposmvvm.domain.model.Repository
import com.google.gson.annotations.SerializedName

/*
data class ApiResponse(
    val id: Int,
    val name: String,
    val description: String?
) {
    fun toDomain(): Repository {
        return Repository(
            id = id,
            name = name,
            description = description ?: "No description"
        )
    }
}

 */
/*data class ApiResponse(
    @SerializedName("items")
    val items: List<RepositoryDto>
)

 */
data class ApiResponse(
    val items: List<RepositoryDto>
)

data class RepoItem(
    val id: Long,
    val name: String,
    val full_name: String,
    val description: String?,
    val owner: Owner,
    val stargazers_count: Int
)

data class Owner(
    val login: String,
    val avatar_url: String
)