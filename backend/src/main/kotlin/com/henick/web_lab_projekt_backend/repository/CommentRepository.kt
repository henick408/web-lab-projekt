package com.henick.web_lab_projekt_backend.repository

import com.henick.web_lab_projekt_backend.entity.Comment
import org.springframework.data.jpa.repository.JpaRepository

interface CommentRepository : JpaRepository<Comment, Long> {
}