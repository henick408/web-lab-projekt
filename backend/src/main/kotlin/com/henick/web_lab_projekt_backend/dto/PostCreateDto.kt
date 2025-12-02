package com.henick.web_lab_projekt_backend.dto

data class PostCreateDto(
    val username: String,
    val title: String,
    val content: String,
    val category: CategoryCreatePostDto
)