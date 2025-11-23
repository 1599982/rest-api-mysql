package com.yart.fuckapi.controller;

import com.yart.fuckapi.dto.CandidateRequest;
import com.yart.fuckapi.dto.CandidateResponse;
import com.yart.fuckapi.dto.CandidateUpdateRequest;
import com.yart.fuckapi.model.Candidate;
import com.yart.fuckapi.model.Office;
import com.yart.fuckapi.service.CandidateService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/candidates")
@RequiredArgsConstructor
@CrossOrigin(origins = "*")
public class CandidateController {
    
    private final CandidateService candidateService;
    
    @PostMapping
    public ResponseEntity<?> createCandidate(@RequestBody CandidateRequest request) {
        try {
            Candidate candidate = candidateService.createCandidate(
                request.getDni(),
                request.getPoliticalParty(),
                request.getDescription(),
                request.getImageUri(),
                request.getRoleType()
            );
            
            CandidateResponse response = new CandidateResponse(
                candidate.getDni(),
                candidate.getPoliticalParty(),
                candidate.getDescription(),
                candidate.getImageUri(),
                candidate.getRoleType(),
                candidate.getVotes(),
                "Candidate created successfully"
            );
            
            return ResponseEntity.status(HttpStatus.CREATED).body(response);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body(new ErrorResponse(e.getMessage()));
        }
    }
    
    @PutMapping("/{dni}")
    public ResponseEntity<?> updateCandidate(@PathVariable String dni, 
                                            @RequestBody CandidateUpdateRequest request) {
        try {
            Candidate candidate = candidateService.updateCandidate(
                dni,
                request.getPoliticalParty(),
                request.getDescription(),
                request.getImageUri(),
                request.getRoleType(),
                request.getVotes()
            );
            
            CandidateResponse response = new CandidateResponse(
                candidate.getDni(),
                candidate.getPoliticalParty(),
                candidate.getDescription(),
                candidate.getImageUri(),
                candidate.getRoleType(),
                candidate.getVotes(),
                "Candidate updated successfully"
            );
            
            return ResponseEntity.ok(response);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body(new ErrorResponse(e.getMessage()));
        }
    }
    
    @GetMapping
    public ResponseEntity<List<CandidateResponse>> getAllCandidates() {
        List<Candidate> candidates = candidateService.getAllCandidates();
        List<CandidateResponse> responses = candidates.stream()
            .map(c -> new CandidateResponse(
                c.getDni(),
                c.getPoliticalParty(),
                c.getDescription(),
                c.getImageUri(),
                c.getRoleType(),
                c.getVotes(),
                null
            ))
            .collect(Collectors.toList());
        
        return ResponseEntity.ok(responses);
    }
    
    @GetMapping("/role/{roleType}")
    public ResponseEntity<List<CandidateResponse>> getCandidatesByRole(@PathVariable Office roleType) {
        List<Candidate> candidates = candidateService.getCandidatesByRoleType(roleType);
        List<CandidateResponse> responses = candidates.stream()
            .map(c -> new CandidateResponse(
                c.getDni(),
                c.getPoliticalParty(),
                c.getDescription(),
                c.getImageUri(),
                c.getRoleType(),
                c.getVotes(),
                null
            ))
            .collect(Collectors.toList());
        
        return ResponseEntity.ok(responses);
    }
    
    @GetMapping("/{dni}")
    public ResponseEntity<?> getCandidateByDni(@PathVariable String dni) {
        try {
            Candidate candidate = candidateService.getCandidateByDni(dni);
            CandidateResponse response = new CandidateResponse(
                candidate.getDni(),
                candidate.getPoliticalParty(),
                candidate.getDescription(),
                candidate.getImageUri(),
                candidate.getRoleType(),
                candidate.getVotes(),
                null
            );
            return ResponseEntity.ok(response);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body(new ErrorResponse(e.getMessage()));
        }
    }
    
    private record ErrorResponse(String error) {}
}
