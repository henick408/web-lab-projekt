package com.henick.web_lab_projekt_backend.mapper

import com.henick.web_lab_projekt_backend.dto.CategoryPostDto
import com.henick.web_lab_projekt_backend.dto.CategoryDto
import com.henick.web_lab_projekt_backend.entity.Category

interface CategoryMapper {
    fun mapToDto(category: Category): CategoryDto
    fun mapFromDto(categoryDto: CategoryDto): Category

    fun mapToPostDto(category: Category): CategoryPostDto
    fun mapFromPostDto(categoryDto: CategoryPostDto): Category
}