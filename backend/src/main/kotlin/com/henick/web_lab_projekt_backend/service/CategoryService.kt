package com.henick.web_lab_projekt_backend.service

import com.henick.web_lab_projekt_backend.entity.Category

interface CategoryService {
    fun getAll(): List<Category>
    fun getById(id: Long): Category?
    fun create(category: Category): Category
    fun existsByName(name: String): Boolean
}