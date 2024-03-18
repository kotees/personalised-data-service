package org.example.personaliseddataservice.controller;

import org.example.personaliseddataservice.domain.PersonalisedDataDTO;
import org.example.personaliseddataservice.service.ProductService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/product")
public class ProductController {

    private final ProductService productService;

    public ProductController(final ProductService productService) {
        this.productService = productService;
    }

    @GetMapping("/shopper")
    public PersonalisedDataDTO getProductsByShopper(@RequestParam final String shopperId,
                    @RequestParam(required = false) final String category,
                    @RequestParam(required = false) final String brand,
                    @RequestParam(required = false, defaultValue = "10") final int limit) {

        return this.productService.getProductsByShopperId(shopperId, category, brand, limit);
    }
}
