package org.example.personaliseddataservice.service.impl;

import java.util.List;

import org.example.personaliseddataservice.domain.PersonalisedDataDTO;
import org.example.personaliseddataservice.domain.ProductMetadataDTO;
import org.example.personaliseddataservice.entity.PersonalisedProductData;
import org.example.personaliseddataservice.entity.ProductMetadata;
import org.example.personaliseddataservice.entity.Shelf;
import org.example.personaliseddataservice.repository.PersonalisedDataRepository;
import org.example.personaliseddataservice.repository.ProductMetadataRepository;
import org.example.personaliseddataservice.repository.ShelfRepository;
import org.example.personaliseddataservice.service.DataReceiverService;
import org.springframework.stereotype.Service;

import jakarta.transaction.Transactional;

@Service
public class DataReceiverServiceImpl implements DataReceiverService {

    private final PersonalisedDataRepository personalisedDataRepository;
    private final ShelfRepository shelfRepository;
    private final ProductMetadataRepository productMetadataRepository;

    public DataReceiverServiceImpl(final PersonalisedDataRepository personalisedDataRepository,
                    final ShelfRepository shelfRepository,
                    final ProductMetadataRepository productMetadataRepository) {

        this.personalisedDataRepository = personalisedDataRepository;
        this.shelfRepository = shelfRepository;
        this.productMetadataRepository = productMetadataRepository;
    }

    @Override
    @Transactional
    public String persistPersonalisedData(final PersonalisedDataDTO personalisedData) {

        try {

            final PersonalisedProductData productData = new PersonalisedProductData();
            productData.setShopperId(personalisedData.getShopperId());
            final PersonalisedProductData savedProductData = this.personalisedDataRepository.save(productData);

            final List<Shelf> shelves = personalisedData.getShelf().parallelStream().map(shelfDTO -> {
                final Shelf shelf = new Shelf();
                shelf.setPersonalisedProductData(savedProductData);
                shelf.setProductId(shelfDTO.getProductId());
                shelf.setRelevancyScore(shelfDTO.getRelevancyScore());
                return shelf;
            }).toList();

            this.shelfRepository.saveAll(shelves);
        } catch (Exception ex) {

            throw new RuntimeException("Exception while persisting personalised data", ex);
        }

        return "Successfully Processed";
    }

    @Override
    public String persistProductMetadata(final ProductMetadataDTO productMetadataDTO) {

        try {

            final ProductMetadata productMetadata = new ProductMetadata();
            productMetadata.setProductId(productMetadataDTO.getProductId());
            productMetadata.setCategory(productMetadataDTO.getCategory());
            productMetadata.setBrand(productMetadataDTO.getBrand());
            this.productMetadataRepository.save(productMetadata);
        } catch (Exception ex) {

            throw new RuntimeException("Exception while persisting personalised data", ex);
        }

        return "Successfully Processed";
    }
}
