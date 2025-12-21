package com.henick.web_lab_projekt_backend.mapper.impl

import com.henick.web_lab_projekt_backend.dto.*
import com.henick.web_lab_projekt_backend.entity.Category
import com.henick.web_lab_projekt_backend.mapper.CategoryMapper
import org.springframework.stereotype.Component

@Component
class CategoryMapperImpl : CategoryMapper{
    override fun mapToResponseDto(category: Category): CategoryResponseDto {
        return CategoryResponseDto(
            name = category.name,
            id = category.id
        )
    }

    override fun mapFromResponseDto(categoryDto: CategoryResponseDto): Category {
        return Category(
            name = categoryDto.name,
            id = categoryDto.id
        )
    }

    override fun mapToRequestDto(category: Category): CategoryRequestDto {
        return CategoryRequestDto(
            name = category.name
        )
    }

    override fun mapFromRequestDto(categoryDto: CategoryRequestDto): Category {
        return Category(
            name = categoryDto.name
        )
    }
}