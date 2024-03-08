package com.example.jsontest.controllers;

import com.example.jsontest.dto.PersonDTO;
import com.example.jsontest.entities.Person;
import com.example.jsontest.services.PersonService;
import com.fasterxml.jackson.core.JsonProcessingException;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.security.NoSuchAlgorithmException;


@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/v1/jsontest")
@RequiredArgsConstructor
public class PersonController {

    private final PersonService personService;

    @PostMapping(path = "/create")
    public ResponseEntity<Person> create(@RequestBody PersonDTO personDTO) throws JsonProcessingException, NoSuchAlgorithmException {
        return personService.createPerson(personDTO);

    }

    @GetMapping(path = "/id/{id}")
    public ResponseEntity<PersonDTO> findById(@PathVariable Long id) throws IOException {
        return personService.findPersonById(id);
    }

    @PutMapping(path = "/id/{id}")
    public ResponseEntity<String> update(@RequestBody PersonDTO personDTO,@PathVariable Long id) throws IOException, NoSuchAlgorithmException {
        return personService.update(personDTO,id);
    }


}
