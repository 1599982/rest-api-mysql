package com.yart.fuckapi.controller;

import com.yart.fuckapi.dto.PersonRequest;
import com.yart.fuckapi.dto.PersonResponse;
import com.yart.fuckapi.model.Person;
import com.yart.fuckapi.service.MigoApiService;
import com.yart.fuckapi.service.PersonService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/persons")
@RequiredArgsConstructor
@CrossOrigin(origins = "*")
public class PersonController {
    
    private final PersonService personService;
    private final MigoApiService migoApiService;
    
    @PostMapping("/register")
    public ResponseEntity<PersonResponse> registerPerson(@RequestBody PersonRequest request) {
        boolean existedBefore = personService.personExists(request.getDni());
        Person person = personService.registerPerson(request.getDni());
        String nombre = migoApiService.getNameByDni(person.getDni());
        
        PersonResponse response = new PersonResponse(
            person.getDni(),
            nombre,
            !existedBefore,
            person.getVotePresident(),
            person.getVoteMayor(),
            existedBefore ? "Person already registered" : "Person registered successfully"
        );
        
        return ResponseEntity.ok(response);
    }
}
