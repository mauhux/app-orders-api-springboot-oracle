package dev.mauhux.apps.orders.security.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Table(name = "SEG_AUTHORITY")
@Data
public class AuthorityEntity {

    @Id
    @Column(name = "AUTHORITY_ID")
    private Long id = 0L;

    @Column(name = "NAME")
    private String name = "";
}
