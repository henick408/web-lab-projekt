package com.henick.web_lab_projekt_backend.service.impl

import com.henick.web_lab_projekt_backend.entity.Comment
import com.henick.web_lab_projekt_backend.repository.CommentRepository
import com.henick.web_lab_projekt_backend.service.CommentService
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.stereotype.Service

@Service
class CommentServiceImpl(private val commentRepository: CommentRepository) : CommentService{
    override fun getAllForPost(postId: Long): List<Comment> {
        return commentRepository.getCommentsByPostId(postId)
    }

    override fun getAllForPostPaged(postId: Long, pageable: Pageable): Page<Comment> {
        return commentRepository.getCommentsByPostId(postId, pageable)
    }

}