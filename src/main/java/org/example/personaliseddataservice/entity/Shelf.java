package org.example.personaliseddataservice.entity;

import org.hibernate.annotations.GenericGenerator;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Data
@Table(name = "shelf")
public class Shelf {

    @Id
    @GeneratedValue(generator = "system-uuid")
    @GenericGenerator(name = "system-uuid", strategy = "uuid2")
    @JsonProperty("id")
    @Column(name = "id")
    private String id;
    @Column(name = "product_id")
    private String productId;
    @Column(name = "relevancy_score")
    private double relevancyScore;
    @ManyToOne
    @JoinColumn(name = "personalised_product_id")
    @JsonIgnore
    private PersonalisedProductData personalisedProductData;
}
