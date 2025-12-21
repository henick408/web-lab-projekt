package com.henick.web_lab_projekt_backend.service.impl

import com.henick.web_lab_projekt_backend.entity.Category
import com.henick.web_lab_projekt_backend.repository.CategoryRepository
import com.henick.web_lab_projekt_backend.service.CategoryService
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Service

@Service
class CategoryServiceImpl(private val categoryRepository: CategoryRepository) : CategoryService{

    override fun getAll(): List<Category> {
        return categoryRepository.findAll()
    }

    override fun getById(id: Long): Category? {
        return categoryRepository.findByIdOrNull(id)
    }

    override fun getByNameLike(name: String): List<Category> {
        return categoryRepository.findByNameContainingIgnoreCase(name)
    }

    override fun create(category: Category): Category {
        return categoryRepository.save(category)
    }

    override fun existsByName(name: String): Boolean {
        return categoryRepository.existsCategoryByName(name)
    }

    override fun deleteById(id: Long) {
        categoryRepository.deleteById(id)
    }

    override fun existsById(id: Long): Boolean {
        return categoryRepository.existsById(id)
    }

    override fun update(id: Long, category: Category): Category {
        category.id = id
        return categoryRepository.save(category)
    }

}