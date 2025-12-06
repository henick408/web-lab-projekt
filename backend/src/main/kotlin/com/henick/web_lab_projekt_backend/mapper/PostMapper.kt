package com.henick.web_lab_projekt_backend.mapper

import com.henick.web_lab_projekt_backend.dto.post.PostBasicDto
import com.henick.web_lab_projekt_backend.dto.post.PostCreateCommentDto
import com.henick.web_lab_projekt_backend.dto.post.PostCreateDto
import com.henick.web_lab_projekt_backend.dto.post.PostUpdateDto
import com.henick.web_lab_projekt_backend.entity.Post

interface PostMapper {
    fun mapToBasicDto(post: Post): PostBasicDto
    fun mapFromBasicDto(postDto: PostBasicDto): Post

    fun mapToCreateDto(post: Post): PostCreateDto
    fun mapFromCreateDto(postDto: PostCreateDto): Post

    fun mapToUpdateDto(post: Post): PostUpdateDto
    fun mapFromUpdateDto(postDto: PostUpdateDto): Post

    fun mapToCreateCommentDto(post: Post): PostCreateCommentDto
    fun mapFromCreateCommentDto(postDto: PostCreateCommentDto): Post
}