package com.henick.web_lab_projekt_backend.mapper

import com.henick.web_lab_projekt_backend.dto.comment.CommentCreateDto
import com.henick.web_lab_projekt_backend.dto.comment.CommentDto
import com.henick.web_lab_projekt_backend.dto.comment.CommentUpdateDto
import com.henick.web_lab_projekt_backend.entity.Comment
import com.henick.web_lab_projekt_backend.entity.Post

interface CommentMapper {
    fun mapToDto(comment: Comment): CommentDto
    fun mapFromDto(commentDto: CommentDto): Comment

    fun mapToCreateDto(comment: Comment): CommentCreateDto
    fun mapFromCreateDto(commentDto: CommentCreateDto, post: Post): Comment

    fun mapToUpdateDto(comment: Comment): CommentUpdateDto
    fun mapFromUpdateDto(commentDto: CommentUpdateDto, post: Post): Comment
}