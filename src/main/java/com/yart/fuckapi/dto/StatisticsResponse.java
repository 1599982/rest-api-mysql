package com.yart.fuckapi.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class StatisticsResponse {
    private int totalVotes;
    private int totalCandidates;
    private TopCandidateInfo topPresident;
    private TopCandidateInfo topMayor;
    
    @Data
    @AllArgsConstructor
    public static class TopCandidateInfo {
        private String nombre;
        private String politicalParty;
        private int votes;
    }
}
