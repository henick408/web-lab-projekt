package com.henick.web_lab_projekt_backend.service.impl

import com.henick.web_lab_projekt_backend.repository.PostRepository
import com.henick.web_lab_projekt_backend.service.PostService
import org.springframework.stereotype.Service

@Service
class PostServiceImpl(private val postRepository: PostRepository) : PostService{
}