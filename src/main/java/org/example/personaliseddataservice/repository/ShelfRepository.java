package org.example.personaliseddataservice.repository;

import java.util.List;

import org.example.personaliseddataservice.entity.PersonalisedProductData;
import org.example.personaliseddataservice.entity.Shelf;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ShelfRepository extends JpaRepository<Shelf, String> {

    List<Shelf> findAllByPersonalisedProductData(final PersonalisedProductData personalisedProductData);
}
