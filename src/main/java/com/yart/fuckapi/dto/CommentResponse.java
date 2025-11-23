package com.yart.fuckapi.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
public class CommentResponse {
    private Long id;
    private String dni;
    private LocalDateTime datetime;
    private String description;
    private String message;
}
