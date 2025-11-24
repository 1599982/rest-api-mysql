package com.yart.fuckapi.dto;

import lombok.Data;

@Data
public class AdminLoginRequest {
    private String dni;
    private String email;
    private String password;
}
