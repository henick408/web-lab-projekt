package com.henick.web_lab_projekt_backend.entity

import jakarta.persistence.CascadeType
import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.FetchType
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import jakarta.persistence.JoinColumn
import jakarta.persistence.ManyToOne
import jakarta.persistence.OneToMany
import jakarta.persistence.OneToOne
import jakarta.persistence.PrePersist
import java.time.LocalDateTime

@Entity
class Post(
    @Column(nullable = false)
    val username: String,
    @Column(nullable = false)
    var title: String,
    @Column(nullable = false)
    var content: String,
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "column_id")
    var category: Category,
    @OneToMany(
        mappedBy = "post",
        cascade = [CascadeType.ALL],
        orphanRemoval = true
    )
    val comments: MutableList<Comment> = mutableListOf(),
    @Column(nullable = false)
    val createdAt: LocalDateTime = LocalDateTime.now(),
    @Column(nullable = false)
    var updatedAt: LocalDateTime = LocalDateTime.now(),
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long? = null
)