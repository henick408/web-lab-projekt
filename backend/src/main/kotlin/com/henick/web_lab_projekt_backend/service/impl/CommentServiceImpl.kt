package com.henick.web_lab_projekt_backend.service.impl

import com.henick.web_lab_projekt_backend.entity.Comment
import com.henick.web_lab_projekt_backend.repository.CommentRepository
import com.henick.web_lab_projekt_backend.service.CommentService
import org.springframework.data.domain.*
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Service
import java.time.LocalDateTime

@Service
class CommentServiceImpl(private val commentRepository: CommentRepository) : CommentService{
    override fun getAllForPost(postId: Long): List<Comment> {
        return commentRepository.findCommentsByPostId(postId)
    }

    override fun getAllForPostPaged(postId: Long, pageable: Pageable): Page<Comment> {
        return commentRepository.findCommentsByPostId(postId, pageable)
    }

    override fun getForPostByCommentId(
        postId: Long,
        commentId: Long
    ): Comment? {
        return commentRepository.findCommentByPostIdAndId(postId, commentId)
    }

    override fun getById(commentId: Long): Comment? {
        return commentRepository.findByIdOrNull(commentId)
    }

    override fun existsById(commentId: Long): Boolean {
        return commentRepository.existsById(commentId)
    }

    override fun create(comment: Comment): Comment {
        comment.createdAt = LocalDateTime.now()
        return commentRepository.save(comment)
    }

    override fun update(
        id: Long,
        comment: Comment
    ): Comment {
        val existingComment = commentRepository.findByIdOrNull(id) ?: throw NoSuchElementException("Cannot update comment that doesn't exist")
        comment.id = id
        comment.createdAt = existingComment.createdAt
        return commentRepository.save(comment)
    }

    override fun deleteById(id: Long) {
        commentRepository.deleteById(id)
    }

}