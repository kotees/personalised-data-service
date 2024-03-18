package org.example.personaliseddataservice.service.impl;

import java.util.List;

import org.example.personaliseddataservice.domain.PersonalisedDataDTO;
import org.example.personaliseddataservice.domain.ProductMetadataDTO;
import org.example.personaliseddataservice.entity.ProductMetadata;
import org.example.personaliseddataservice.entity.ProductPersonalization;
import org.example.personaliseddataservice.repository.ProductMetadataRepository;
import org.example.personaliseddataservice.repository.ProductPersonalizationRepository;
import org.example.personaliseddataservice.service.DataReceiverService;
import org.springframework.stereotype.Service;

import jakarta.transaction.Transactional;

@Service
public class DataReceiverServiceImpl implements DataReceiverService {

    private final ProductPersonalizationRepository productPersonalizationRepository;
    private final ProductMetadataRepository productMetadataRepository;

    public DataReceiverServiceImpl(final ProductPersonalizationRepository productPersonalizationRepository,
                    final ProductMetadataRepository productMetadataRepository) {

        this.productPersonalizationRepository = productPersonalizationRepository;
        this.productMetadataRepository = productMetadataRepository;
    }

    @Override
    @Transactional
    public String persistPersonalisedData(final PersonalisedDataDTO personalisedData) {

        try {

            final List<ProductPersonalization> shelves = personalisedData.getShelf()
                            .parallelStream()
                            .map(shelfDTO -> {
                                final ProductPersonalization productPersonalization = new ProductPersonalization();
                                productPersonalization.setShopperId(personalisedData.getShopperId());
                                productPersonalization.setProductId(shelfDTO.getProductId());
                                productPersonalization.setRelevancyScore(shelfDTO.getRelevancyScore());
                                return productPersonalization;
                            })
                            .toList();

            this.productPersonalizationRepository.saveAll(shelves);
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
