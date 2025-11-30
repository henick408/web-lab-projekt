package com.henick.web_lab_projekt_backend.repository

import com.henick.web_lab_projekt_backend.entity.Post
import org.springframework.data.jpa.repository.JpaRepository


interface PostRepository : JpaRepository<Post, Long>{
    fun existsPostById(id: Long): Boolean
}