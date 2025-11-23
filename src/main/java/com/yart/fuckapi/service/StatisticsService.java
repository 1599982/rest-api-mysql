package com.yart.fuckapi.service;

import com.yart.fuckapi.dto.StatisticsResponse;
import com.yart.fuckapi.model.Candidate;
import com.yart.fuckapi.model.Office;
import com.yart.fuckapi.repository.CandidateRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;

@Service
@RequiredArgsConstructor
public class StatisticsService {
    
    private final CandidateRepository candidateRepository;
    private final MigoApiService migoApiService;
    
    public StatisticsResponse getStatistics() {
        List<Candidate> allCandidates = candidateRepository.findAll();
        
        int totalVotes = allCandidates.stream()
            .mapToInt(c -> c.getVotes() != null ? c.getVotes() : 0)
            .sum();
        
        int totalCandidates = allCandidates.size();
        
        List<Candidate> presidents = candidateRepository.findByRoleType(Office.PRESIDENT);
        Candidate topPresident = presidents.stream()
            .max(Comparator.comparingInt(c -> c.getVotes() != null ? c.getVotes() : 0))
            .orElse(null);
        
        List<Candidate> mayors = candidateRepository.findByRoleType(Office.MAYOR);
        Candidate topMayor = mayors.stream()
            .max(Comparator.comparingInt(c -> c.getVotes() != null ? c.getVotes() : 0))
            .orElse(null);
        
        StatisticsResponse.TopCandidateInfo topPresidentInfo = null;
        if (topPresident != null) {
            String nombre = migoApiService.getNameByDni(topPresident.getDni());
            topPresidentInfo = new StatisticsResponse.TopCandidateInfo(
                nombre,
                topPresident.getPoliticalParty(),
                topPresident.getVotes() != null ? topPresident.getVotes() : 0
            );
        }
        
        StatisticsResponse.TopCandidateInfo topMayorInfo = null;
        if (topMayor != null) {
            String nombre = migoApiService.getNameByDni(topMayor.getDni());
            topMayorInfo = new StatisticsResponse.TopCandidateInfo(
                nombre,
                topMayor.getPoliticalParty(),
                topMayor.getVotes() != null ? topMayor.getVotes() : 0
            );
        }
        
        return new StatisticsResponse(
            totalVotes,
            totalCandidates,
            topPresidentInfo,
            topMayorInfo
        );
    }
}
