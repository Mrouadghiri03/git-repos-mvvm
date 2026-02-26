package com.example.gitreposmvvm.domain.model



data class Repository(
    val id: Long,
    val name: String,
    val fullName: String,
    val description: String?,
    val owner: String,
    val stars: Int,
    val avatarUrl: String
)
