package com.henick.web_lab_projekt_backend.dto.comment


data class CommentCreateDto(
    val username: String,
    val content: String,
    val postId: Long
)
