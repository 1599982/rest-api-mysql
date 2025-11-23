package com.yart.fuckapi.dto;

import com.yart.fuckapi.model.Office;
import lombok.Data;

@Data
public class CandidateRequest {
    private String dni;
    private String politicalParty;
    private String description;
    private String imageUri;
    private Office roleType;
}
