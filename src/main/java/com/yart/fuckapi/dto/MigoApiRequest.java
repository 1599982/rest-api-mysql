package com.yart.fuckapi.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class MigoApiRequest {
    private String token;
    private String dni;
}
