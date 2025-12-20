package com.henick.web_lab_projekt_backend.dto

import com.henick.web_lab_projekt_backend.validation.*
import jakarta.validation.constraints.*

data class CommentRequestDto(

    @field:NotBlank(
        message = "Nazwa użytkownika nie może być pusta",
        groups = [OnCreate::class]
    )
    @field:Size(
        max = 255, message = "Nazwa użytkownika zbyt długa",
        groups = [OnCreate::class]
    )
    val username: String?,

    @field:NotBlank(
        message = "Treść komentarza nie może być pusta",
        groups = [OnCreate::class, OnUpdate::class]
    )
    @field:Size(
        max = 4000,
        message = "Treść komentarza zbyt długa",
        groups = [OnCreate::class, OnUpdate::class]
    )
    val content: String?,

    @field:NotNull(
        message = "Id posta nie moze być puste",
        groups = [OnCreate::class]
    )
    var postId: Long?

)
