package com.henick.web_lab_projekt_backend.mapper

import com.henick.web_lab_projekt_backend.dto.*
import com.henick.web_lab_projekt_backend.entity.Post

interface PostMapper {
    fun mapToResponseDto(post: Post): PostResponseDto
    fun mapFromResponseDto(postDto: PostResponseDto): Post

    fun mapToRequestDto(post: Post): PostRequestDto
    fun mapFromRequestDto(postDto: PostRequestDto): Post

}