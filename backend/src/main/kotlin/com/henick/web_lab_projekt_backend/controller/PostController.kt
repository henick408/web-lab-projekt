package com.henick.web_lab_projekt_backend.controller

import com.henick.web_lab_projekt_backend.dto.PostBasicDto
import com.henick.web_lab_projekt_backend.dto.PostCreateDto
import com.henick.web_lab_projekt_backend.dto.PostUpdateDto
import com.henick.web_lab_projekt_backend.entity.Post
import com.henick.web_lab_projekt_backend.mapper.PostMapper
import com.henick.web_lab_projekt_backend.service.PostService
import org.springframework.http.ResponseEntity
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
class PostController(private val postService: PostService, private val postMapper: PostMapper) {

    @GetMapping
    fun getAllPosts(): ResponseEntity<List<PostBasicDto>>{
        val posts = postService.getAll()
        val postBasicDtos = posts.stream().map{post -> postMapper.mapToBasicDto(post)}.toList()
        return ResponseEntity.ok(postBasicDtos)
    }

    @GetMapping("/{id}")
    fun getPostById(@PathVariable id: Long): ResponseEntity<PostBasicDto> {
        val post = postService.getById(id)
        if (post == null){
            return ResponseEntity.notFound().build()
        }
        return ResponseEntity.ok(postMapper.mapToBasicDto(post))
    }

    @PostMapping
    fun createPost(@RequestBody postCreateDto: PostCreateDto): ResponseEntity<PostBasicDto> {
        val post = postMapper.mapFromCreateDto(postCreateDto)
        val createdPost = postService.create(post)
        val outputPostDto = postMapper.mapToBasicDto(createdPost)
        val location = URI.create("/posts/${outputPostDto.id}")
        return ResponseEntity.created(location).body(outputPostDto)
    }

    @PutMapping("/{id}")
    fun updatePost(
        @RequestBody updateDto: PostUpdateDto,
        @PathVariable id: Long
    ): ResponseEntity<PostBasicDto> {
        if(!postService.existsById(id)){
            return ResponseEntity.notFound().build()
        }
        val updatePost = postMapper.mapFromUpdateDto(updateDto)
        val updatedPost: Post = postService.update(id, updatePost)
        val outputPostDto = postMapper.mapToBasicDto(updatedPost)
        return ResponseEntity.ok(outputPostDto)
    }

}