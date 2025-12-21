package com.henick.web_lab_projekt_backend.controller

import com.henick.web_lab_projekt_backend.dto.*
import com.henick.web_lab_projekt_backend.mapper.CommentMapper
import com.henick.web_lab_projekt_backend.service.*
import com.henick.web_lab_projekt_backend.validation.*
import org.springframework.http.ResponseEntity
import org.springframework.validation.annotation.Validated
import org.springframework.web.bind.annotation.*
import java.net.URI

@RestController
@RequestMapping("/api/comments")
class CommentController(
    private val commentService: CommentService,
    val commentMapper: CommentMapper,
    private val postService: PostService
) {

    @GetMapping("/{id}")
    fun getCommentById(@PathVariable id: Long): ResponseEntity<CommentResponseDto> {
        val comment = commentService.getById(id)
        if (comment == null) {
            return ResponseEntity.notFound().build()
        }
        val outputCommentDto = commentMapper.mapToResponseDto(comment)

        return ResponseEntity.ok(outputCommentDto)
    }

    @PostMapping
    fun createComment(@Validated(OnCreate::class) @RequestBody commentDto: CommentRequestDto): ResponseEntity<CommentResponseDto> {
        val post = postService.getById(commentDto.postId!!) ?: return ResponseEntity.badRequest().build()
        val comment = commentMapper.mapFromRequestDto(commentDto, post)
        val createdComment = commentService.create(comment)
        val outputCommentDto = commentMapper.mapToResponseDto(createdComment)

        val commentId = outputCommentDto.id
        val location = URI("/api/posts/${createdComment.post.id}/comments/$commentId")

        return ResponseEntity.created(location).body(outputCommentDto)
    }

    @PutMapping("/{id}")
    fun updateComment(
        @PathVariable id: Long,
        @Validated(value = [OnUpdate::class]) @RequestBody commentDto: CommentRequestDto
    ): ResponseEntity<CommentResponseDto>{
        val comment = commentService.getById(id) ?: return ResponseEntity.notFound().build()

        val post = postService.getById(comment.post.id!!)!!
        val username = comment.username
        val inputComment = commentMapper.mapFromRequestDto(commentDto, post)
        inputComment.username = username
        val updatedComment = commentService.update(id, inputComment)
        val outputCommentDto = commentMapper.mapToResponseDto(updatedComment)

        return ResponseEntity.ok(outputCommentDto)
    }

    @DeleteMapping("/{id}")
    fun deleteComment(@PathVariable id: Long): ResponseEntity<Unit> {
        if (!commentService.existsById(id)) {
            return ResponseEntity.notFound().build()
        }
        commentService.deleteById(id)

        return ResponseEntity.noContent().build()
    }

}