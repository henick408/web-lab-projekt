package com.henick.web_lab_projekt_backend.dto.comment

import java.time.LocalDateTime

data class CommentDto(
    val username: String,
    val content: String,
    val createdAt: LocalDateTime,
    val id: Long?
)