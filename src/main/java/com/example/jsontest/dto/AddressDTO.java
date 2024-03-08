package com.example.jsontest.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

public record AddressDTO(
@JsonProperty("streetName")
String streetName,
@JsonProperty("streetNumber")
int streetNumber,
@JsonProperty("city")
String city,
@JsonProperty("zipCode")
String zipCode
) {
}
