package com.henick.web_lab_projekt_backend.entity

import jakarta.persistence.*

@Entity
class Category(
    @Column(nullable = false, unique = true)
    var name: String,
    @OneToMany(
        mappedBy = "category"
    )
    val posts: MutableList<Post> = mutableListOf(),
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long? = null
)