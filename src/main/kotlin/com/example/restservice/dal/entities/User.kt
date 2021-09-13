package com.example.restservice.dal.entities

import com.example.restservice.dal.entities.additions.Status
import org.springframework.data.annotation.CreatedDate
import org.springframework.data.annotation.LastModifiedDate
import java.time.LocalDateTime
import java.util.*
import javax.persistence.*


//@MappedSuperclass
@Entity
@Table(name = "users")
class User(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Int,

    @Column(name = "username")
    val username: String,

    @Column(name = "email")
    val email: String,

    @Column(name = "password")
    val password: String,

    @Column(nullable = true)
    @ManyToMany(cascade = [CascadeType.ALL])
    @JoinTable(name = "user_roles",
        joinColumns = [JoinColumn(name = "user_id", referencedColumnName = "id")],
        inverseJoinColumns = [JoinColumn(name = "role_id", referencedColumnName = "id")]
    )
    val roles: List<Role> = mutableListOf(),

    @CreatedDate
    @Column(name = "created")
    var created: LocalDateTime = LocalDateTime.now(),

    @LastModifiedDate
    @Column(name = "updated")
    var updated: LocalDateTime,

    @Enumerated(EnumType.STRING)
    @Column(name = "status")
    var status: Status
)

//    @field: OneToMany(targetEntity = Role::class) var roles: MutableSet<Role> = mutableSetOf()){
//
//    //Convert this class to Spring Security's User object
//    fun toUser() : User {
//        val authorities = mutableSetOf()
//        roles.forEach { authorities.add(SimpleGrantedAuthority(it.role)) }
//        return User(userName, password,enabled, accountNonExpired, credentialsNonExpired, accountNonLocked,authorities);
//    }