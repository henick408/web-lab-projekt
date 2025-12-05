package com.henick.web_lab_projekt_backend.service

import com.henick.web_lab_projekt_backend.entity.Post
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable

interface PostService {

    fun getAll(): List<Post>
    fun getById(id: Long): Post?
    fun getAllPaged(pageable: Pageable): Page<Post>
    fun create(post: Post): Post
    fun update(id: Long, post: Post): Post
    fun existsById(id: Long): Boolean
    fun deleteById(id: Long)
}