package org.example.personaliseddataservice.controller;

import org.example.personaliseddataservice.domain.PersonalisedDataDTO;
import org.example.personaliseddataservice.domain.ProductMetadataDTO;
import org.example.personaliseddataservice.service.DataReceiverService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/data")
public class DataReceiverController {

    private final DataReceiverService dataReceiverService;

    public DataReceiverController(final DataReceiverService dataReceiverService) {
        this.dataReceiverService = dataReceiverService;
    }

    @PostMapping("/receive/personalised")
    public String receiveData(@RequestBody final PersonalisedDataDTO personalisedData) {

        return this.dataReceiverService.persistPersonalisedData(personalisedData);
    }

    @PostMapping("/receive/metadata")
    public String receiveData(@RequestBody final ProductMetadataDTO productMetadataDTO) {

        return this.dataReceiverService.persistProductMetadata(productMetadataDTO);
    }
}
