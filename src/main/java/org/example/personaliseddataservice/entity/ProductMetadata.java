package org.example.personaliseddataservice.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table(name = "product_metadata")
public class ProductMetadata {

    @Id
    @Column(name = "productId")
    private String productId;

    @Column(name = "category")
    private String category;

    @Column(name = "brand")
    private String brand;
}
