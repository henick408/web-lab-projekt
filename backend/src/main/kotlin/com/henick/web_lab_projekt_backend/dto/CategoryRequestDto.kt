package com.henick.web_lab_projekt_backend.dto

import com.henick.web_lab_projekt_backend.validation.*
import jakarta.validation.constraints.*

data class CategoryRequestDto(

    @field:NotBlank(
        message = "Nazwa kategorii nie może być pusta",
        groups = [OnCreate::class, OnUpdate::class]
    )
    @field:Size(
        max = 255,
        message = "Nazwa kategorii zbyt długa",
        groups = [OnCreate::class, OnUpdate::class]
    )
    val name: String
)
