package com.example.gitreposmvvm.data.model


data class ApiResponse(
    val items: List<RepositoryDto>
)

data class Owner(
    val login: String,
    val avatar_url: String
)