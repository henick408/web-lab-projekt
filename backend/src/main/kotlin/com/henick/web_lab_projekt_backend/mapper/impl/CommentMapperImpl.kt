package com.henick.web_lab_projekt_backend.mapper.impl

import com.henick.web_lab_projekt_backend.dto.*
import com.henick.web_lab_projekt_backend.entity.*
import com.henick.web_lab_projekt_backend.mapper.CommentMapper
import org.springframework.stereotype.Component

@Component
class CommentMapperImpl() : CommentMapper{
    override fun mapToResponseDto(comment: Comment): CommentResponseDto {
        return CommentResponseDto(
            username = comment.username,
            content = comment.content,
            createdAt = comment.createdAt!!,
            id = comment.id
        )
    }

    override fun mapFromResponseDto(commentDto: CommentResponseDto): Comment {
        return Comment(
            username = commentDto.username,
            content = commentDto.content,
            createdAt = commentDto.createdAt,
            id = commentDto.id,
            post = Post("", "", "", Category(""))
        )
    }

    override fun mapToRequestDto(comment: Comment): CommentRequestDto {
        return CommentRequestDto(
            username = comment.username,
            content = comment.content,
            postId = comment.post.id!!
        )
    }

    override fun mapFromRequestDto(commentDto: CommentRequestDto, post: Post): Comment {
        return Comment(
            username = commentDto.username,
            content = commentDto.content,
            post = post
        )
    }

    override fun mapToUpdateDto(comment: Comment): CommentUpdateDto {
        return CommentUpdateDto(
            content = comment.content
        )
    }

    override fun mapFromUpdateDto(
        commentDto: CommentUpdateDto,
        post: Post
    ): Comment {
        return Comment(
            username = "",
            content = commentDto.content,
            post = post
        )
    }
}