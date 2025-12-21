package com.henick.web_lab_projekt_backend.service

import com.henick.web_lab_projekt_backend.entity.Comment
import org.springframework.data.domain.*

interface CommentService {
    fun getAllForPost(postId: Long): List<Comment>
    fun getAllForPostPaged(postId: Long, pageable: Pageable): Page<Comment>
    fun getForPostByCommentId(postId: Long, commentId: Long): Comment?
    fun getById(commentId: Long): Comment?
    fun existsById(commentId: Long): Boolean
    fun create(comment: Comment): Comment
    fun update(id: Long, comment: Comment): Comment
    fun deleteById(id: Long)
}