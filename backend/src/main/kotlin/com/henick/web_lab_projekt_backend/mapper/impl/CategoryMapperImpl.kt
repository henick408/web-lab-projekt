package com.henick.web_lab_projekt_backend.mapper.impl

import com.henick.web_lab_projekt_backend.dto.CategoryPostDto
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

    override fun mapToPostDto(category: Category): CategoryPostDto {
        return CategoryPostDto(
            id = category.id
        )
    }

    override fun mapFromPostDto(categoryDto: CategoryPostDto): Category {
        return Category(
            id = categoryDto.id,
            name = ""
        )
    }
}