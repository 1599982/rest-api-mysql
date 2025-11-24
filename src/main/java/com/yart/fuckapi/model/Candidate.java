package com.yart.fuckapi.model;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "candidate")
@Data
public class Candidate {

    @Id
    @Column(nullable = false)
    private String dni;

    @OneToOne
    @JoinColumn(name = "dni", referencedColumnName = "dni", insertable = false, updatable = false)
    private Person person;

    @Column(name = "political_party", nullable = false)
    private String politicalParty;

    @Column(nullable = false, columnDefinition = "TEXT")
    private String description;

    @Column(name = "image_uri", nullable = false)
    private String imageUri;

    @Enumerated(EnumType.STRING)
    @Column(name = "role_type", nullable = false)
    private Office roleType;

    @Column
    private Integer votes;

    @Column(nullable = false)
    private Boolean enabled = true;
}
