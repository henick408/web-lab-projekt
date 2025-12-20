package com.henick.web_lab_projekt_backend.controller

import com.henick.web_lab_projekt_backend.dto.CategoryRequestDto
import com.henick.web_lab_projekt_backend.dto.CategoryResponseDto
import com.henick.web_lab_projekt_backend.mapper.CategoryMapper
import com.henick.web_lab_projekt_backend.service.CategoryService
import jakarta.validation.Valid
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
    fun getAllCategories(@RequestParam name: String?): ResponseEntity<List<CategoryResponseDto>> {
        if(name == null) {
            val categories = categoryService.getAll()
            val categoryDtos = categories.map { category -> categoryMapper.mapToResponseDto(category) }.toList()
            return ResponseEntity.ok(categoryDtos)
        }
        val categories = categoryService.getByNameLike(name)
        val categoryDtos = categories.map { category -> categoryMapper.mapToResponseDto(category) }
        return ResponseEntity.ok(categoryDtos)

    }

    @GetMapping("/{id}")
    fun getCategoryById(@PathVariable id: Long): ResponseEntity<CategoryResponseDto> {
        val category = categoryService.getById(id)
        if (category == null) {
            return ResponseEntity.notFound().build()
        }
        val categoryDto = categoryMapper.mapToResponseDto(category)
        return ResponseEntity.ok(categoryDto)
    }

    @PostMapping()
    fun createCategory(@Valid @RequestBody categoryDto: CategoryRequestDto): ResponseEntity<CategoryResponseDto> {
        val category = categoryMapper.mapFromRequestDto(categoryDto)
        if (categoryService.existsByName(categoryDto.name)){
            return ResponseEntity(HttpStatus.CONFLICT)
        }
        val createdCategory = categoryService.create(category)
        val outputCategoryDto = categoryMapper.mapToResponseDto(createdCategory)
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
        @Valid @RequestBody categoryDto: CategoryRequestDto
    ): ResponseEntity<CategoryResponseDto> {
        if(!categoryService.existsById(id)) {
            return ResponseEntity.notFound().build()
        }
        val category = categoryMapper.mapFromRequestDto(categoryDto)
        category.id = id
        val categoryOutputDto = categoryMapper.mapToResponseDto(category)
        return ResponseEntity.ok(categoryOutputDto)
    }


}