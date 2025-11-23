package com.yart.fuckapi.model;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "person")
@Data
public class Person {
    
    @Id
    @Column(nullable = false)
    private String dni;
    
    @Column(name = "vote_president")
    private String votePresident;
    
    @Column(name = "vote_mayor")
    private String voteMayor;
}
