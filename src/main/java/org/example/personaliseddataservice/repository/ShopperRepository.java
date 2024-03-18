package org.example.personaliseddataservice.repository;

import org.example.personaliseddataservice.entity.Shopper;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ShopperRepository extends JpaRepository<Shopper, String> {

}
