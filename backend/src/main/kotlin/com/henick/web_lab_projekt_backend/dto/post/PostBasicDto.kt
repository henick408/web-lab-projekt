package com.henick.web_lab_projekt_backend.dto.post

import com.henick.web_lab_projekt_backend.dto.category.CategoryDto
import java.time.LocalDateTime

data class PostBasicDto(
    val username: String,
    var title: String,
    var content: String,
    var category: CategoryDto,
    val createdAt: LocalDateTime?,
    var updatedAt: LocalDateTime?,
    val id: Long?
    )