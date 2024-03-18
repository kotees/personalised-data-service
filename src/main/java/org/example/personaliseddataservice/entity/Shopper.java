package org.example.personaliseddataservice.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table(name = "shopper")
public class Shopper {

    @Id
    @Column(name = "shopper_id")
    private String shopperId;

    @Column(name = "name")
    private String name;

    @Column(name = "address")
    private String address;
}
