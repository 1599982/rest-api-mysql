package com.yart.fuckapi.controller;

import com.yart.fuckapi.dto.AdminLoginRequest;
import com.yart.fuckapi.dto.AdminLoginResponse;
import com.yart.fuckapi.model.Admin;
import com.yart.fuckapi.service.AdminService;
import com.yart.fuckapi.service.MigoApiService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/api/admin")
@RequiredArgsConstructor
@CrossOrigin(origins = "*")
public class AdminController {
    
    private final AdminService adminService;
    private final MigoApiService migoApiService;
    
    @PostMapping("/verify")
    public ResponseEntity<AdminLoginResponse> verifyAdmin(@RequestBody AdminLoginRequest request) {
        Optional<Admin> adminOpt = adminService.verifyAdmin(
            request.getDni(),
            request.getEmail(),
            request.getPassword()
        );
        
        if (adminOpt.isPresent()) {
            Admin admin = adminOpt.get();
            String nombre = migoApiService.getNameByDni(admin.getDni());
            AdminLoginResponse response = new AdminLoginResponse(
                true,
                admin.getDni(),
                nombre,
                admin.getEmail(),
                "Admin authenticated successfully"
            );
            return ResponseEntity.ok(response);
        } else {
            AdminLoginResponse response = new AdminLoginResponse(
                false,
                null,
                null,
                null,
                "Invalid credentials"
            );
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(response);
        }
    }
}
