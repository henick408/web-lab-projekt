package com.henick.web_lab_projekt_backend.controller

import com.henick.web_lab_projekt_backend.dto.CommentResponseDto
import com.henick.web_lab_projekt_backend.dto.PostResponseDto
import com.henick.web_lab_projekt_backend.dto.PostRequestDto
import com.henick.web_lab_projekt_backend.dto.PostUpdateDto
import com.henick.web_lab_projekt_backend.mapper.CommentMapper
import com.henick.web_lab_projekt_backend.mapper.PostMapper
import com.henick.web_lab_projekt_backend.service.CommentService
import com.henick.web_lab_projekt_backend.service.PostService
import jakarta.validation.Valid
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.data.domain.Sort
import org.springframework.data.web.PageableDefault
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.PutMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

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
        val post = postService.getById(id)
        if (post == null){
            return ResponseEntity.notFound().build()
        }
        return ResponseEntity.ok(postMapper.mapToResponseDto(post))
    }

    @PostMapping
    fun createPost(@Valid @RequestBody postCreateDto: PostRequestDto): ResponseEntity<PostResponseDto> {
        val post = postMapper.mapFromRequestDto(postCreateDto)
        val createdPost = postService.create(post)
        val outputPostDto = postMapper.mapToResponseDto(createdPost)
        val location = URI.create("/posts/${outputPostDto.id}")
        return ResponseEntity.created(location).body(outputPostDto)
    }

    @PutMapping("/{id}")
    fun updatePost(
        @Valid @RequestBody updateDto: PostUpdateDto,
        @PathVariable id: Long
    ): ResponseEntity<PostResponseDto> {
        val post = postService.getById(id)
        if(post == null){
            return ResponseEntity.notFound().build()
        }
        val username = post.username
        val inputPost = postMapper.mapFromUpdateDto(updateDto)
        inputPost.username = username
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

        val comment = commentService.getForPostByCommentId(postId, commentId)

        if (comment == null){
            return ResponseEntity.notFound().build()
        }

        val commentDto = commentMapper.mapToResponseDto(comment)

        return ResponseEntity.ok(commentDto)

    }

}