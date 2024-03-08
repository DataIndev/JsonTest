package com.example.jsontest.dto;

import com.example.jsontest.entities.Address;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;

@Builder
public record PersonDTO(
@JsonProperty("firstName")
String firstName,
@JsonProperty("lastName")
String lastName,
@JsonProperty("address")
Address address

) {

}
