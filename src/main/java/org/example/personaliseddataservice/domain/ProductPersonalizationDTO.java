package org.example.personaliseddataservice.domain;

import lombok.Data;

@Data
public class ProductPersonalizationDTO {

    private String productId;
    private Double relevancyScore;
}
