package com.henick.web_lab_projekt_backend.mapper.impl

import com.henick.web_lab_projekt_backend.dto.CategoryCreateDto
import com.henick.web_lab_projekt_backend.dto.CategoryCreatePostDto
import com.henick.web_lab_projekt_backend.dto.CategoryDto
import com.henick.web_lab_projekt_backend.entity.Category
import com.henick.web_lab_projekt_backend.mapper.CategoryMapper
import org.springframework.stereotype.Component

@Component
class CategoryMapperImpl : CategoryMapper{
    override fun mapToDto(category: Category): CategoryDto {
        return CategoryDto(
            name = category.name,
            id = category.id
        )
    }

    override fun mapFromDto(categoryDto: CategoryDto): Category {
        return Category(
            name = categoryDto.name,
            id = categoryDto.id
        )
    }

    override fun mapToPostDto(category: Category): CategoryCreatePostDto {
        return CategoryCreatePostDto(
            id = category.id
        )
    }

    override fun mapFromPostDto(categoryDto: CategoryCreatePostDto): Category {
        return Category(
            id = categoryDto.id,
            name = ""
        )
    }

    override fun mapToCreateDto(category: Category): CategoryCreateDto {
        return CategoryCreateDto(
            name = category.name
        )
    }

    override fun mapFromCreateDto(categoryDto: CategoryCreateDto): Category {
        return Category(
            name = categoryDto.name
        )
    }
}