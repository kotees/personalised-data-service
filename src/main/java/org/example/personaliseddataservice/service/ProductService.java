package org.example.personaliseddataservice.service;

import org.example.personaliseddataservice.domain.PersonalisedDataDTO;

public interface ProductService {

    PersonalisedDataDTO getProductsByShopperId(final String shopperId, final String category, final String brand,
                    final int limit);
}
