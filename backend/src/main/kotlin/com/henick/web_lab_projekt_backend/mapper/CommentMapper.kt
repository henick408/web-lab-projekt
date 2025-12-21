package com.henick.web_lab_projekt_backend.mapper

import com.henick.web_lab_projekt_backend.dto.*
import com.henick.web_lab_projekt_backend.entity.*

interface CommentMapper {
    fun mapToResponseDto(comment: Comment): CommentResponseDto
    fun mapFromResponseDto(commentDto: CommentResponseDto): Comment

    fun mapToRequestDto(comment: Comment): CommentRequestDto
    fun mapFromRequestDto(commentDto: CommentRequestDto, post: Post): Comment

}