package com.example.restservice.dal.entities

import org.springframework.data.annotation.LastModifiedDate
import java.time.LocalDateTime
import javax.persistence.*

@Entity
@Table(name = "articles")
class Article(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Int,

    var title: String,

    var content: String,

    @ManyToOne
    var user: User,

    var created: LocalDateTime = LocalDateTime.now(),

    @LastModifiedDate
    @Column(name = "updated")
    var updated: LocalDateTime
)
