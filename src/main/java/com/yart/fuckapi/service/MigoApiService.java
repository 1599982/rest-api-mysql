package com.yart.fuckapi.service;

import com.yart.fuckapi.dto.MigoApiRequest;
import com.yart.fuckapi.dto.MigoApiResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
@RequiredArgsConstructor
public class MigoApiService {
    
    private static final String MIGO_API_URL = "https://api.migo.pe/api/v1/dni";
    private static final String MIGO_TOKEN = "G4e5tb5fcm5vlsBjSFeixD2CFjBboyNdLx0rNJ3QCWgP3ZMEGzvSl0AEiqwS";
    
    private final RestTemplate restTemplate;
    
    public String getNameByDni(String dni) {
        try {
            MigoApiRequest request = new MigoApiRequest(MIGO_TOKEN, dni);
            MigoApiResponse response = restTemplate.postForObject(
                MIGO_API_URL,
                request,
                MigoApiResponse.class
            );
            
            if (response != null && response.isSuccess()) {
                return response.getNombre();
            }
            return null;
        } catch (Exception e) {
            return null;
        }
    }
}
