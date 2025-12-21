package com.henick.web_lab_projekt_backend.mapper.impl

import com.henick.web_lab_projekt_backend.dto.*
import com.henick.web_lab_projekt_backend.entity.*
import com.henick.web_lab_projekt_backend.mapper.*
import org.springframework.stereotype.Component

@Component
class PostMapperImpl(private val categoryMapper: CategoryMapper) : PostMapper {
    override fun mapToResponseDto(post: Post): PostResponseDto {
        val categoryDto = categoryMapper.mapToResponseDto(post.category)
        return PostResponseDto(
            username = post.username,
            title = post.title,
            content = post.content,
            category = categoryDto,
            createdAt = post.createdAt,
            updatedAt = post.updatedAt,
            id = post.id
        )
    }

    override fun mapFromResponseDto(postDto: PostResponseDto): Post {
        val category = categoryMapper.mapFromResponseDto(postDto.category)
        return Post(
            username = postDto.username,
            title = postDto.title,
            content = postDto.content,
            category = category,
            createdAt = postDto.createdAt,
            updatedAt = postDto.updatedAt,
            id = postDto.id
        )
    }

    override fun mapToRequestDto(post: Post): PostRequestDto {
        return PostRequestDto(
            username = post.username,
            title = post.title,
            content = post.content,
            categoryId = post.category.id
        )
    }

    override fun mapFromRequestDto(postDto: PostRequestDto): Post {
        val category = Category("", postDto.categoryId)
        return Post(
            username = postDto.username!!,
            title = postDto.title!!,
            content = postDto.content!!,
            category = category
        )
    }


}