package com.yart.fuckapi.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class PersonResponse {
    private String dni;
    private boolean newRegistration;
    private String message;
}
