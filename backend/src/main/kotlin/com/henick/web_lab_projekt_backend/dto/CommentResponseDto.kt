package com.henick.web_lab_projekt_backend.dto

import java.time.LocalDateTime

data class CommentResponseDto(
    val username: String,
    val content: String,
    val createdAt: LocalDateTime,
    val id: Long?
)