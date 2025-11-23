package com.yart.fuckapi.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class PersonResponse {
    private String dni;
    private String nombre;
    private boolean newRegistration;
    private boolean hasVotedPresident;
    private boolean hasVotedMayor;
    private String message;
}
