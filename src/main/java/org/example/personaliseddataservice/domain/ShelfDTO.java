package org.example.personaliseddataservice.domain;

import lombok.Data;

@Data
public class ShelfDTO {

    private String productId;
    private Double relevancyScore;
}
