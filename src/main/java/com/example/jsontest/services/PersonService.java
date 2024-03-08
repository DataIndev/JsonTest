package com.example.jsontest.services;

import com.example.jsontest.dto.PersonDTO;
import com.example.jsontest.entities.Address;
import com.example.jsontest.entities.Person;
import com.example.jsontest.repositories.PersonRepo;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

@Service
@AllArgsConstructor
public class PersonService {
    private final PersonRepo personRepo;

    public ResponseEntity<Person> createPerson(PersonDTO personDTO) throws JsonProcessingException, NoSuchAlgorithmException {
        ObjectMapper map = new ObjectMapper();
        var jsonAddress = map.writeValueAsString(personDTO.address());
        var person = Person.builder()
        .firstName(personDTO.firstName())
        .lastName(personDTO.lastName())
        .address(jsonAddress)
        .checksum(calculateChecksum(personDTO.toString().getBytes()))
        .build();
        return new ResponseEntity<>(personRepo.save(person), HttpStatus.CREATED);

    }


    public ResponseEntity<PersonDTO> findPersonById(Long id) throws IOException {
        var personReference = personRepo.getReferenceById(id);

        Address address = new ObjectMapper()
        .readerFor(Address.class)
        .readValue(personReference.getAddress());
        var persona = PersonDTO.builder()
        .firstName(personReference.getFirstName())
        .lastName(personReference.getLastName())
        .address(address)
        .build();

        return new ResponseEntity<>(persona,HttpStatus.OK);

    }

    public ResponseEntity<String> update(PersonDTO personDTO, Long id) throws NoSuchAlgorithmException {

        if(calculateChecksum(personDTO.toString().getBytes()).equals(personRepo.getReferenceById(id).getChecksum())){
            System.out.println("Son iguales");
            return new ResponseEntity<>(HttpStatus.OK);
        }else{
            System.out.println("no son iguales");
            return new ResponseEntity<>(HttpStatus.OK);
        }
    }











        private  String calculateChecksum(byte[]data) throws NoSuchAlgorithmException {
        MessageDigest digest = MessageDigest.getInstance("SHA-256");
        byte[] checksumBytes = digest.digest(data);
        StringBuilder checksum = new StringBuilder();
        for(byte b : checksumBytes){
            checksum.append(String.format("%02x", b));
        }
        return checksum.toString();
    }
}
