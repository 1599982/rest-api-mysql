package com.yart.fuckapi.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class AdminLoginResponse {
    private boolean authenticated;
    private String dni;
    private String nombre;
    private String email;
    private String message;
}
