package com.henick.web_lab_projekt_backend.repository

import com.henick.web_lab_projekt_backend.entity.Comment
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.data.jpa.repository.JpaRepository

interface CommentRepository : JpaRepository<Comment, Long> {
    fun getCommentsByPostId(postId: Long): List<Comment>
    fun getCommentsByPostId(postId: Long, pageable: Pageable): Page<Comment>
}