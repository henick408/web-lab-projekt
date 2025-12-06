package com.henick.web_lab_projekt_backend.dto.post

import com.henick.web_lab_projekt_backend.dto.category.CategoryCreatePostDto

data class PostCreateDto(
    val username: String,
    val title: String,
    val content: String,
    val category: CategoryCreatePostDto
)