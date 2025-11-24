package com.yart.fuckapi.service;

import com.yart.fuckapi.model.Admin;
import com.yart.fuckapi.repository.AdminRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AdminService {
    
    private final AdminRepository adminRepository;
    
    public Optional<Admin> verifyAdmin(String dni, String email, String password) {
        Optional<Admin> adminOpt = adminRepository.findById(dni);
        
        if (adminOpt.isEmpty()) {
            return Optional.empty();
        }
        
        Admin admin = adminOpt.get();
        
        if (admin.getEmail().equals(email) && admin.getPassword().equals(password)) {
            return Optional.of(admin);
        }
        
        return Optional.empty();
    }
}
