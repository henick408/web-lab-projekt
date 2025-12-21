package com.henick.web_lab_projekt_backend.entity

import jakarta.persistence.*
import java.time.LocalDateTime

@Entity
class Post(

    @Column(nullable = false)
    var username: String,

    @Column(nullable = false)
    var title: String,

    @Column(nullable = false)
    var content: String,

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "category_id")
    var category: Category,

    @Column(name = "created_at", nullable = false)
    var createdAt: LocalDateTime? = null,

    @Column(name = "updated_at", nullable = false)
    var updatedAt: LocalDateTime? = null,

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long? = null

)