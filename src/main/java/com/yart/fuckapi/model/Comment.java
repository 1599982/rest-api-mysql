package com.yart.fuckapi.model;

import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;

@Entity
@Table(name = "comment")
@Data
public class Comment {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @Column(nullable = false)
    private String dni;
    
    @ManyToOne
    @JoinColumn(name = "dni", insertable = false, updatable = false)
    private Person person;
    
    @CreationTimestamp
    @Column(nullable = false, updatable = false)
    private LocalDateTime datetime;
    
    @Column(nullable = false, columnDefinition = "TEXT")
    private String description;
}
