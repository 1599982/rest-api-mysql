package com.yart.fuckapi.dto;

import lombok.Data;

@Data
public class MigoApiResponse {
    private boolean success;
    private String dni;
    private String nombre;
}
