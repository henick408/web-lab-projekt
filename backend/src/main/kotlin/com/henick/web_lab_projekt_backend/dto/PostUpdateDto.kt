package com.henick.web_lab_projekt_backend.dto

data class PostUpdateDto(
    val title: String,
    val content: String,
    val category: CategoryPostDto
)
