package com.henick.web_lab_projekt_backend.repository

import com.henick.web_lab_projekt_backend.entity.Category
import org.springframework.data.jpa.repository.JpaRepository

interface CategoryRepository : JpaRepository<Category, Long> {
    fun existsCategoryByName(name: String): Boolean
    fun findByNameContainingIgnoreCase(name: String): List<Category>
}