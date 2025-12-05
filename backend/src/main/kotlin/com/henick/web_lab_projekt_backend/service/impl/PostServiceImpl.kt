package com.henick.web_lab_projekt_backend.service.impl

import com.henick.web_lab_projekt_backend.entity.Post
import com.henick.web_lab_projekt_backend.repository.PostRepository
import com.henick.web_lab_projekt_backend.service.PostService
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Service
import java.time.LocalDateTime

@Service
class PostServiceImpl(private val postRepository: PostRepository) : PostService{

    override fun getAll(): List<Post> =
        postRepository.findAll()

    override fun getById(id: Long): Post? =
        postRepository.findByIdOrNull(id)

    override fun getAllPaged(pageable: Pageable): Page<Post> {
        return postRepository.findAll(pageable)
    }

    override fun create(post: Post): Post {
        post.createdAt = LocalDateTime.now()
        post.updatedAt = LocalDateTime.now()
        return postRepository.save(post)
    }

    override fun update(id: Long, post: Post): Post {
        val existingPost = postRepository.findByIdOrNull(id)
        post.id = id
        post.createdAt = existingPost?.createdAt
        post.updatedAt = LocalDateTime.now()
        return postRepository.save(post)
    }

    override fun existsById(id: Long): Boolean {
        return postRepository.existsPostById(id)
    }

    override fun deleteById(id: Long) {
        postRepository.deleteById(id)
    }

}