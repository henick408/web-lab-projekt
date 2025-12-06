package com.henick.web_lab_projekt_backend.mapper

import com.henick.web_lab_projekt_backend.dto.category.CategoryCreateDto
import com.henick.web_lab_projekt_backend.dto.category.CategoryCreatePostDto
import com.henick.web_lab_projekt_backend.dto.category.CategoryDto
import com.henick.web_lab_projekt_backend.entity.Category

interface CategoryMapper {
    fun mapToDto(category: Category): CategoryDto
    fun mapFromDto(categoryDto: CategoryDto): Category

    fun mapToPostDto(category: Category): CategoryCreatePostDto
    fun mapFromPostDto(categoryDto: CategoryCreatePostDto): Category

    fun mapToCreateDto(category: Category): CategoryCreateDto
    fun mapFromCreateDto(categoryDto: CategoryCreateDto): Category
}