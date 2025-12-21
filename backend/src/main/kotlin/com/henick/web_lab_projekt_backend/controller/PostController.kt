package com.henick.web_lab_projekt_backend.controller

import com.henick.web_lab_projekt_backend.dto.*
import com.henick.web_lab_projekt_backend.mapper.*
import com.henick.web_lab_projekt_backend.service.*
import com.henick.web_lab_projekt_backend.validation.*
import org.springframework.data.domain.*
import org.springframework.data.web.PageableDefault
import org.springframework.http.ResponseEntity
import org.springframework.validation.annotation.Validated
import org.springframework.web.bind.annotation.*

import java.net.URI

@RestController
@RequestMapping("/api/posts")
class PostController(
    private val postService: PostService,
    private val postMapper: PostMapper,
    private val commentService: CommentService,
    private val commentMapper: CommentMapper
) {

    @GetMapping
    fun getAllPosts(
        @PageableDefault(
            page = 0,
            size = 10,
            sort = ["createdAt"],
            direction = Sort.Direction.DESC
        )
        pageable: Pageable
    ): ResponseEntity<Page<PostResponseDto>>{
        val page = postService.getAllPaged(pageable)
        val pageDto = page.map{post -> postMapper.mapToResponseDto(post)}
        return ResponseEntity.ok(pageDto)
    }

    @GetMapping("/{id}")
    fun getPostById(@PathVariable id: Long): ResponseEntity<PostResponseDto> {
        val post = postService.getById(id) ?: return ResponseEntity.notFound().build()
        return ResponseEntity.ok(postMapper.mapToResponseDto(post))
    }

    @PostMapping
    fun createPost(@Validated(OnCreate::class) @RequestBody postCreateDto: PostRequestDto): ResponseEntity<PostResponseDto> {
        val post = postMapper.mapFromRequestDto(postCreateDto)
        val createdPost = postService.create(post)
        val outputPostDto = postMapper.mapToResponseDto(createdPost)
        val location = URI.create("/posts/${outputPostDto.id}")
        return ResponseEntity.created(location).body(outputPostDto)
    }

    @PutMapping("/{id}")
    fun updatePost(
        @Validated(OnUpdate::class) @RequestBody updateDto: PostRequestDto,
        @PathVariable id: Long
    ): ResponseEntity<PostResponseDto> {
        //val post = postService.getById(id) ?: return ResponseEntity.notFound().build()
        //val username = post.username
        val inputPost = postMapper.mapFromRequestDto(updateDto)
        //inputPost.username = username
        val updatedPost = postService.update(id, inputPost)
        val outputPostDto = postMapper.mapToResponseDto(updatedPost)
        return ResponseEntity.ok(outputPostDto)
    }

    @DeleteMapping("/{id}")
    fun deletePost(@PathVariable id: Long): ResponseEntity<Unit>{
        if(!postService.existsById(id)){
            return ResponseEntity.notFound().build()
        }
        postService.deleteById(id)
        return ResponseEntity.noContent().build()
    }

    @GetMapping("/{id}/comments")
    fun getCommentsFromPost(
        @PageableDefault(
            page = 0,
            size = 5,
            sort = ["createdAt"],
            direction = Sort.Direction.DESC
        )
        pageable: Pageable,
        @PathVariable id: Long
    ): ResponseEntity<Page<CommentResponseDto>> {
        if (!postService.existsById(id)) {
            return ResponseEntity.notFound().build()
        }
        val comments = commentService.getAllForPostPaged(id, pageable)
        val commentDtos = comments.map{comment -> commentMapper.mapToResponseDto(comment)}
        return ResponseEntity.ok(commentDtos)
    }

    @GetMapping("/{postId}/comments/{commentId}")
    fun getCommentFromPostByCommentId(
        @PathVariable postId: Long,
        @PathVariable commentId: Long
    ): ResponseEntity<CommentResponseDto>{

        val comment =
            commentService.getForPostByCommentId(postId, commentId) ?: return ResponseEntity.notFound().build()

        val commentDto = commentMapper.mapToResponseDto(comment)

        return ResponseEntity.ok(commentDto)

    }

}