package com.henick.web_lab_projekt_backend.dto

import jakarta.validation.constraints.*
import com.henick.web_lab_projekt_backend.validation.*

data class PostRequestDto(

    @field:NotBlank(
        message = "Nazwa użytkownika nie może być pusta",
        groups = [OnCreate::class]
    )
    val username: String?,

    @field:NotBlank(
        message = "Tytuł posta nie może być pusty",
        groups = [OnCreate::class, OnUpdate::class]
    )
    @field:Size(
        max = 255,
        message = "Tytuł posta zbyt długi",
        groups = [OnCreate::class, OnUpdate::class]
    )
    val title: String?,

    @field:NotBlank(
        message = "Treść posta nie może być pusta",
        groups = [OnCreate::class, OnUpdate::class]
    )
    @field:Size(
        max = 4000,
        message = "Treść posta zbyt długa",
        groups = [OnCreate::class, OnUpdate::class]
    )
    val content: String?,

    @field:NotNull(
        message = "Id posta nie może być puste",
        groups = [OnCreate::class]
    )
    var categoryId: Long?

)