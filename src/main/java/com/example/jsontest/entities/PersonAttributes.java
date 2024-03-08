package com.example.jsontest.entities;

import com.fasterxml.jackson.annotation.JsonAnyGetter;
import lombok.Data;
import java.util.Map;

public class PersonAttributes {
    private Long id;
    public String hair;
    private Map<String, Object> properties;
    @JsonAnyGetter
    public Map<String, Object> getProperties(){
        return properties;
    }
}
