package com.example.restservice.dal.entities

import com.example.restservice.dal.entities.additions.Status
import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import org.springframework.data.annotation.CreatedDate
import org.springframework.data.annotation.LastModifiedDate
import java.time.LocalDateTime
import java.util.*
import javax.persistence.*

@Entity
@Table(name = "roles")
class Role(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Int,

    @Column(name = "name")
    var name: String,

    @ManyToMany(mappedBy = "roles")
    val users: List<User> = mutableListOf(),

    @CreatedDate
    @Column(name = "created")
    var created: LocalDateTime = LocalDateTime.now(),

    @LastModifiedDate
    @Column(name = "updated")
    var updated: LocalDateTime = LocalDateTime.now(),
)