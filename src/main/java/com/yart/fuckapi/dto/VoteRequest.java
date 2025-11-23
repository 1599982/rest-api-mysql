package com.yart.fuckapi.dto;

import lombok.Data;

@Data
public class VoteRequest {
    private String voterDni;
    private String candidateDni;
}
