package com.yart.fuckapi.controller;

import com.yart.fuckapi.dto.CommentRequest;
import com.yart.fuckapi.dto.CommentResponse;
import com.yart.fuckapi.model.Comment;
import com.yart.fuckapi.service.CommentService;
import com.yart.fuckapi.service.MigoApiService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/comments")
@RequiredArgsConstructor
@CrossOrigin(origins = "*")
public class CommentController {
    
    private final CommentService commentService;
    private final MigoApiService migoApiService;
    
    @PostMapping
    public ResponseEntity<?> createComment(@RequestBody CommentRequest request) {
        try {
            Comment comment = commentService.createComment(request.getDni(), request.getDescription());
            String nombre = migoApiService.getNameByDni(comment.getDni());
            
            CommentResponse response = new CommentResponse(
                comment.getId(),
                comment.getDni(),
                nombre,
                comment.getDatetime(),
                comment.getDescription(),
                "Comment created successfully"
            );
            
            return ResponseEntity.status(HttpStatus.CREATED).body(response);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body(new ErrorResponse(e.getMessage()));
        }
    }
    
    @GetMapping
    public ResponseEntity<List<CommentResponse>> getAllComments() {
        List<Comment> comments = commentService.getAllComments();
        List<CommentResponse> responses = comments.stream()
            .map(c -> {
                String nombre = migoApiService.getNameByDni(c.getDni());
                return new CommentResponse(
                    c.getId(),
                    c.getDni(),
                    nombre,
                    c.getDatetime(),
                    c.getDescription(),
                    null
                );
            })
            .collect(Collectors.toList());
        
        return ResponseEntity.ok(responses);
    }
    
    @GetMapping("/person/{dni}")
    public ResponseEntity<List<CommentResponse>> getCommentsByDni(@PathVariable String dni) {
        List<Comment> comments = commentService.getCommentsByDni(dni);
        String nombre = migoApiService.getNameByDni(dni);
        List<CommentResponse> responses = comments.stream()
            .map(c -> new CommentResponse(
                c.getId(),
                c.getDni(),
                nombre,
                c.getDatetime(),
                c.getDescription(),
                null
            ))
            .collect(Collectors.toList());
        
        return ResponseEntity.ok(responses);
    }
    
    private record ErrorResponse(String error) {}
}
