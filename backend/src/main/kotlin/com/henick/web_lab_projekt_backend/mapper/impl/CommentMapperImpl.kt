package com.henick.web_lab_projekt_backend.mapper.impl

import com.henick.web_lab_projekt_backend.dto.comment.CommentCreateDto
import com.henick.web_lab_projekt_backend.dto.comment.CommentDto
import com.henick.web_lab_projekt_backend.dto.comment.CommentUpdateDto
import com.henick.web_lab_projekt_backend.entity.Category
import com.henick.web_lab_projekt_backend.entity.Comment
import com.henick.web_lab_projekt_backend.entity.Post
import com.henick.web_lab_projekt_backend.mapper.CommentMapper
import org.springframework.stereotype.Component

@Component
class CommentMapperImpl() : CommentMapper{
    override fun mapToDto(comment: Comment): CommentDto {
        return CommentDto(
            username = comment.username,
            content = comment.content,
            createdAt = comment.createdAt!!,
            id = comment.id
        )
    }

    override fun mapFromDto(commentDto: CommentDto): Comment {
        return Comment(
            username = commentDto.username,
            content = commentDto.content,
            createdAt = commentDto.createdAt,
            id = commentDto.id,
            post = Post("", "", "", Category(""))
        )
    }

    override fun mapToCreateDto(comment: Comment): CommentCreateDto {
        return CommentCreateDto(
            username = comment.username,
            content = comment.content,
            postId = comment.post.id!!
        )
    }

    override fun mapFromCreateDto(commentDto: CommentCreateDto, post: Post): Comment {
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