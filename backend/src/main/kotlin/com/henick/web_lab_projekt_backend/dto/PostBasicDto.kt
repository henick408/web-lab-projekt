package com.henick.web_lab_projekt_backend.dto

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