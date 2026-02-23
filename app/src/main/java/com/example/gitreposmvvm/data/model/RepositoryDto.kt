package com.example.gitreposmvvm.data.model

import com.example.gitreposmvvm.domain.model.Repository
import com.google.gson.annotations.SerializedName

data class RepositoryDto(
    val id: Long,
    val name: String,
    @SerializedName("full_name") val fullName: String,
    val description: String?,
    val stargazers_count: Int,
    val owner: OwnerDto
) {
    data class OwnerDto(
        val login: String,
        @SerializedName("avatar_url") val avatarUrl: String
    )

    fun toDomain(): Repository {
        return Repository(
            id = id,
            name = name,
            fullName = fullName,
            description = description,
            owner = owner.login,
            stars = stargazers_count,
            avatarUrl = owner.avatarUrl
        )
    }
}