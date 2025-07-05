package dev.mauhux.apps.orders.security.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.util.HashSet;
import java.util.Set;

@Data
@Entity
@Table(name = "SEG_USER")
public class UserEntity {

    @Id
    @Column(name = "USER_ID")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "USER_NAME")
    private String userName;

    @Column(name = "PASSWORD")
    private String password;

    @Column(name = "NAME")
    private String name;

    // Many to Many
    @ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinTable(name = "SEG_USER_AUTHORITY", joinColumns = { @JoinColumn(name = "USER_ID") }, inverseJoinColumns = {
            @JoinColumn(name = "AUTHORITY_ID") })
    private Set<AuthorityEntity> authorities = new HashSet<>();
}
