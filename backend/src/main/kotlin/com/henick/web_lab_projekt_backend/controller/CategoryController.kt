package com.henick.web_lab_projekt_backend.controller

import com.henick.web_lab_projekt_backend.dto.category.CategoryCreateDto
import com.henick.web_lab_projekt_backend.dto.category.CategoryDto
import com.henick.web_lab_projekt_backend.mapper.CategoryMapper
import com.henick.web_lab_projekt_backend.service.CategoryService
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.PutMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController
import java.net.URI

@RestController
@RequestMapping("/api/categories")
class CategoryController(private val categoryService: CategoryService, private val categoryMapper: CategoryMapper) {

    @GetMapping()
    fun getAllCategories(@RequestParam name: String?): ResponseEntity<List<CategoryDto>> {
        if(name == null) {
            val categories = categoryService.getAll();
            val categoryDtos = categories.map { category -> categoryMapper.mapToDto(category) }.toList()
            return ResponseEntity.ok(categoryDtos)
        }
        val categories = categoryService.getByNameLike(name)
        val categoryDtos = categories.map { category -> categoryMapper.mapToDto(category) }
        return ResponseEntity.ok(categoryDtos)

    }

    @GetMapping("/{id}")
    fun getCategoryById(@PathVariable id: Long): ResponseEntity<CategoryDto> {
        val category = categoryService.getById(id)
        if (category == null) {
            return ResponseEntity.notFound().build()
        }
        val categoryDto = categoryMapper.mapToDto(category)
        return ResponseEntity.ok(categoryDto)
    }

    @PostMapping()
    fun createCategory(@RequestBody categoryDto: CategoryCreateDto): ResponseEntity<CategoryDto> {
        val category = categoryMapper.mapFromCreateDto(categoryDto)
        if (categoryService.existsByName(categoryDto.name)){
            return ResponseEntity(HttpStatus.CONFLICT)
        }
        val createdCategory = categoryService.create(category)
        val outputCategoryDto = categoryMapper.mapToDto(createdCategory)
        val location = URI("/api/categories/${outputCategoryDto.id}")
        return ResponseEntity.created(location).body(outputCategoryDto)
    }

    @DeleteMapping("/{id}")
    fun deleteCategory(@PathVariable id: Long): ResponseEntity<Unit> {
        if (!categoryService.existsById(id)) {
            return ResponseEntity.notFound().build()
        }
        categoryService.deleteById(id)
        return ResponseEntity.noContent().build()
    }

    @PutMapping("/{id}")
    fun updateCategory(
        @PathVariable id: Long,
        @RequestBody categoryDto: CategoryCreateDto
    ): ResponseEntity<CategoryDto> {
        if(!categoryService.existsById(id)) {
            return ResponseEntity.notFound().build()
        }
        val category = categoryMapper.mapFromCreateDto(categoryDto)
        category.id = id
        val categoryOutputDto = categoryMapper.mapToDto(category)
        return ResponseEntity.ok(categoryOutputDto)
    }


}