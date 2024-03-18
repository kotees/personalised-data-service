package org.example.personaliseddataservice.repository;

import org.example.personaliseddataservice.entity.PersonalisedProductData;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PersonalisedDataRepository extends JpaRepository<PersonalisedProductData, String> {

    PersonalisedProductData findByShopperId(final String shopperId);
}
