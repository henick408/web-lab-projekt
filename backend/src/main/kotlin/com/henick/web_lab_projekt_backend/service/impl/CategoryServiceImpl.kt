package com.henick.web_lab_projekt_backend.service.impl

import com.henick.web_lab_projekt_backend.repository.CategoryRepository
import com.henick.web_lab_projekt_backend.service.CategoryService
import org.springframework.stereotype.Service

@Service
class CategoryServiceImpl(private val repository: CategoryRepository) : CategoryService{
}