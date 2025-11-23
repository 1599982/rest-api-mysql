package com.yart.fuckapi.service;

import com.yart.fuckapi.model.Comment;
import com.yart.fuckapi.repository.CommentRepository;
import com.yart.fuckapi.repository.PersonRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CommentService {
    
    private final CommentRepository commentRepository;
    private final PersonRepository personRepository;
    
    @Transactional
    public Comment createComment(String dni, String description) {
        if (!personRepository.existsById(dni)) {
            throw new IllegalArgumentException("Person with DNI " + dni + " not found");
        }
        
        Comment comment = new Comment();
        comment.setDni(dni);
        comment.setDescription(description);
        
        return commentRepository.save(comment);
    }
    
    public List<Comment> getCommentsByDni(String dni) {
        return commentRepository.findByDni(dni);
    }
    
    public List<Comment> getAllComments() {
        return commentRepository.findAll();
    }
}
