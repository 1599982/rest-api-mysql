package com.yart.fuckapi.model;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "admin")
@Data
public class Admin {
    
    @Id
    @Column(nullable = false)
    private String dni;
    
    @OneToOne
    @MapsId
    @JoinColumn(name = "dni")
    private Person person;
    
    @Column(nullable = false)
    private String email;
    
    @Column(nullable = false)
    private String password;
}
