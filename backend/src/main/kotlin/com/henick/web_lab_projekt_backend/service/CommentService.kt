package com.henick.web_lab_projekt_backend.service

import com.henick.web_lab_projekt_backend.entity.Comment
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable

interface CommentService {
    fun getAllForPost(postId: Long): List<Comment>
    fun getAllForPostPaged(postId: Long, pageable: Pageable): Page<Comment>
}