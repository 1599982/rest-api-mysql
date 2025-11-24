package com.yart.fuckapi.dto;

import com.yart.fuckapi.model.Office;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class CandidateResponse {
    private String dni;
    private String nombre;
    private String politicalParty;
    private String description;
    private String imageUri;
    private Office roleType;
    private Integer votes;
    private Boolean enabled;
    private String message;
}
