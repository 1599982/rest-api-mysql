package com.yart.fuckapi.service;

import com.yart.fuckapi.model.Candidate;
import com.yart.fuckapi.model.Office;
import com.yart.fuckapi.model.Person;
import com.yart.fuckapi.repository.CandidateRepository;
import com.yart.fuckapi.repository.PersonRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CandidateService {
    
    private final CandidateRepository candidateRepository;
    private final PersonRepository personRepository;
    
    @Transactional
    public Candidate createCandidate(String dni, String politicalParty, 
                                    String description, String imageUri, Office roleType) {
        if (candidateRepository.existsById(dni)) {
            throw new IllegalArgumentException("Candidate with DNI " + dni + " already exists");
        }
        
        Person person = personRepository.findById(dni)
            .orElseGet(() -> {
                Person newPerson = new Person();
                newPerson.setDni(dni);
                return personRepository.save(newPerson);
            });
        
        Candidate candidate = new Candidate();
        candidate.setDni(dni);
        candidate.setPoliticalParty(politicalParty);
        candidate.setDescription(description);
        candidate.setImageUri(imageUri);
        candidate.setRoleType(roleType);
        candidate.setVotes(0);
        
        return candidateRepository.save(candidate);
    }
    
    @Transactional
    public Candidate updateCandidate(String dni, String politicalParty,
                                    String description, String imageUri, Office roleType, Integer votes) {
        Candidate candidate = candidateRepository.findById(dni)
            .orElseThrow(() -> new IllegalArgumentException("Candidate with DNI " + dni + " not found"));
        
        if (politicalParty != null) candidate.setPoliticalParty(politicalParty);
        if (description != null) candidate.setDescription(description);
        if (imageUri != null) candidate.setImageUri(imageUri);
        if (roleType != null) candidate.setRoleType(roleType);
        if (votes != null) candidate.setVotes(votes);
        
        return candidateRepository.save(candidate);
    }
    
    public List<Candidate> getAllCandidates() {
        return candidateRepository.findAll();
    }
    
    public List<Candidate> getCandidatesByRoleType(Office roleType) {
        return candidateRepository.findByRoleType(roleType);
    }
    
    public Candidate getCandidateByDni(String dni) {
        return candidateRepository.findById(dni)
            .orElseThrow(() -> new IllegalArgumentException("Candidate with DNI " + dni + " not found"));
    }
}
