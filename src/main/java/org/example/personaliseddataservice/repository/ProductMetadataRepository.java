package org.example.personaliseddataservice.repository;

import org.example.personaliseddataservice.entity.ProductMetadata;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface ProductMetadataRepository extends JpaRepository<ProductMetadata, String> {

    @Query(value = "SELECT product_id, category, brand FROM PRODUCT_METADATA WHERE category = ?1 AND "
                    + "(?2 IS NULL OR brand = ?2)",
                    nativeQuery = true)
    ProductMetadata findByCategoryAndBrand(final String category, final String brand);
}
