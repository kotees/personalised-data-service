package org.example.personaliseddataservice.service.impl;

import java.util.List;

import org.example.personaliseddataservice.domain.PersonalisedDataDTO;
import org.example.personaliseddataservice.domain.ProductPersonalizationDTO;
import org.example.personaliseddataservice.entity.ProductMetadata;
import org.example.personaliseddataservice.entity.ProductPersonalization;
import org.example.personaliseddataservice.repository.ProductMetadataRepository;
import org.example.personaliseddataservice.repository.ProductPersonalizationRepository;
import org.example.personaliseddataservice.service.ProductService;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

@Service
public class ProductServiceImpl implements ProductService {

    private final ProductPersonalizationRepository shelfRepository;
    private final ProductMetadataRepository productMetadataRepository;

    public ProductServiceImpl(final ProductPersonalizationRepository productPersonalizationRepository,
                    final ProductMetadataRepository productMetadataRepository) {
        this.shelfRepository = productPersonalizationRepository;
        this.productMetadataRepository = productMetadataRepository;
    }

    @Override
    public PersonalisedDataDTO getProductsByShopperId(final String shopperId,
                    final String category,
                    final String brand,
                    final int limit) {

        final ProductMetadata productMetadata = this.productMetadataRepository.findByCategoryAndBrand(category,
                        brand);
        if ((StringUtils.hasText(category) || StringUtils.hasText(brand)) && null == productMetadata) {

            return null;
        }

        final String productId = null != productMetadata ? productMetadata.getProductId() : null;
        final List<ProductPersonalization> shelves = this.shelfRepository.findAllByShopperIdAndAndProductId(shopperId,
                        productId,
                        limit);
        final List<ProductPersonalizationDTO> productPersonalizationDTOS = shelves.parallelStream()
                        .map(productPersonalization -> {

                            final ProductPersonalizationDTO productPersonalizationDTO = new ProductPersonalizationDTO();
                            productPersonalizationDTO.setProductId(productPersonalization.getProductId());
                            productPersonalizationDTO.setRelevancyScore(productPersonalization.getRelevancyScore());
                            return productPersonalizationDTO;
                        })
                        .toList();

        final PersonalisedDataDTO personalisedDataDTO = new PersonalisedDataDTO();
        personalisedDataDTO.setShopperId(shopperId);
        personalisedDataDTO.setShelf(productPersonalizationDTOS);
        return personalisedDataDTO;
    }
}
