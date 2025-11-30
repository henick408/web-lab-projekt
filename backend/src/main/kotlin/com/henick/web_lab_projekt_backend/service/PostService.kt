package com.henick.web_lab_projekt_backend.service

import com.henick.web_lab_projekt_backend.entity.Post

interface PostService {

    fun getAll(): List<Post>
    fun getById(id: Long): Post?
    fun create(post: Post): Post
    fun update(id: Long, post: Post): Post
    fun existsById(id: Long): Boolean
}