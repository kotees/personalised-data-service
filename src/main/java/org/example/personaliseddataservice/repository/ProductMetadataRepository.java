package org.example.personaliseddataservice.repository;

import org.example.personaliseddataservice.entity.ProductMetadata;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductMetadataRepository extends JpaRepository<ProductMetadata, String> {

}
