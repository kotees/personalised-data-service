package org.example.personaliseddataservice.service.impl;

import java.util.List;

import org.example.personaliseddataservice.domain.PersonalisedDataDTO;
import org.example.personaliseddataservice.domain.ShelfDTO;
import org.example.personaliseddataservice.entity.PersonalisedProductData;
import org.example.personaliseddataservice.entity.Shelf;
import org.example.personaliseddataservice.repository.PersonalisedDataRepository;
import org.example.personaliseddataservice.repository.ProductMetadataRepository;
import org.example.personaliseddataservice.repository.ShelfRepository;
import org.example.personaliseddataservice.service.ProductService;
import org.springframework.stereotype.Service;

@Service
public class ProductServiceImpl implements ProductService {

    private final PersonalisedDataRepository personalisedDataRepository;
    private final ShelfRepository shelfRepository;
    private final ProductMetadataRepository productMetadataRepository;

    public ProductServiceImpl(final PersonalisedDataRepository personalisedDataRepository,
                    final ShelfRepository shelfRepository,
                    final ProductMetadataRepository productMetadataRepository) {
        this.personalisedDataRepository = personalisedDataRepository;
        this.shelfRepository = shelfRepository;
        this.productMetadataRepository = productMetadataRepository;
    }

    @Override
    public PersonalisedDataDTO getProductsByShopperId(String shopperId, String category, String brand,
                    int limit) {

        final var personalisedProductData = this.personalisedDataRepository.findByShopperId(shopperId);
        final List<Shelf> shelves = this.shelfRepository.findAllByPersonalisedProductData(personalisedProductData);
        final List<ShelfDTO> shelfDTOs = shelves.parallelStream().map(shelf -> {
            final ShelfDTO shelfDTO = new ShelfDTO();
            shelfDTO.setProductId(shelf.getProductId());
            shelfDTO.setRelevancyScore(shelf.getRelevancyScore());
            return shelfDTO;
        }).toList();

        final PersonalisedDataDTO personalisedDataDTO = new PersonalisedDataDTO();
        personalisedDataDTO.setShopperId(shopperId);
        personalisedDataDTO.setShelf(shelfDTOs);
        return personalisedDataDTO;
    }
}
