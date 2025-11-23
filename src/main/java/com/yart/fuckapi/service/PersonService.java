package com.yart.fuckapi.service;

import com.yart.fuckapi.model.Person;
import com.yart.fuckapi.repository.PersonRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class PersonService {
    
    private final PersonRepository personRepository;
    
    @Transactional
    public Person registerPerson(String dni) {
        return personRepository.findById(dni)
                .orElseGet(() -> {
                    Person newPerson = new Person();
                    newPerson.setDni(dni);
                    return personRepository.save(newPerson);
                });
    }
    
    public boolean personExists(String dni) {
        return personRepository.existsById(dni);
    }
}
