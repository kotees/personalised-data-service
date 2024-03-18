package org.example.personaliseddataservice.domain;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

@Data
public class PersonalisedDataDTO {

    @JsonProperty("shopperId")
    private String shopperId;
    @JsonProperty("shelf")
    private List<ShelfDTO> shelf;
}
