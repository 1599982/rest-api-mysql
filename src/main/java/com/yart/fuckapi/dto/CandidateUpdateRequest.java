package com.yart.fuckapi.dto;

import com.yart.fuckapi.model.Office;
import lombok.Data;

@Data
public class CandidateUpdateRequest {
    private String politicalParty;
    private String description;
    private String imageUri;
    private Office roleType;
    private Boolean enabled;
}
