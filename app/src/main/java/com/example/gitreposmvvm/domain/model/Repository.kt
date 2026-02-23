package com.example.gitreposmvvm.domain.model

import com.example.gitreposmvvm.data.model.Owner


data class Repository(
    val id: Long,
    val name: String,
    val fullName: String,
    val description: String?,
    val owner: String,
    val stars: Int,
    val avatarUrl: String
)
