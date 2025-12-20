package com.henick.web_lab_projekt_backend.mapper

import com.henick.web_lab_projekt_backend.dto.*
import com.henick.web_lab_projekt_backend.entity.Category

interface CategoryMapper {
    fun mapToResponseDto(category: Category): CategoryResponseDto
    fun mapFromResponseDto(categoryDto: CategoryResponseDto): Category

    fun mapToRequestDto(category: Category): CategoryRequestDto
    fun mapFromRequestDto(categoryDto: CategoryRequestDto): Category
}