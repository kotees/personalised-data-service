package org.example.personaliseddataservice.service;

import org.example.personaliseddataservice.domain.PersonalisedDataDTO;
import org.example.personaliseddataservice.domain.ProductMetadataDTO;

public interface DataReceiverService {

    String persistPersonalisedData(final PersonalisedDataDTO personalisedData);

    String persistProductMetadata(final ProductMetadataDTO productMetadataDTO);
}
