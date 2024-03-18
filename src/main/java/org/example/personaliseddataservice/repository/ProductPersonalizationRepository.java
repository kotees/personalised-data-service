package org.example.personaliseddataservice.repository;

import java.util.List;

import org.example.personaliseddataservice.entity.ProductPersonalization;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface ProductPersonalizationRepository extends JpaRepository<ProductPersonalization, String> {

    @Query(value = "SELECT id, product_id, relevancy_score, shopper_id from PRODUCT_PERSONALIZATION where shopper_id = ?1 AND "
                    + "(?2 IS NULL OR product_id = ?2) ORDER BY product_id LIMIT ?3", nativeQuery = true)
    List<ProductPersonalization> findAllByShopperIdAndAndProductId(final String shopperId, final String productId, final int limit);
}
