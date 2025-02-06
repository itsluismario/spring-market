package com.spring_market.web.controller;

import com.spring_market.domain.Purchase;
import com.spring_market.domain.service.PurchaseService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@RestController
@RequestMapping("/purchases")
@Tag(name = "Purchase Controller", description = "API endpoints for purchase management")
public class PurchaseController {

    private static final Logger logger = LoggerFactory.getLogger(PurchaseController.class);

    @Autowired
    private PurchaseService purchaseService;

    @Operation(summary = "Get all purchases", description = "Retrieves a list of all purchases")
    @ApiResponse(responseCode = "200", description = "Successfully retrieved all purchases")
    @GetMapping("/all")
    public ResponseEntity<List<Purchase>> getAll() {
        return new ResponseEntity<>(purchaseService.getAll(), HttpStatus.OK);
    }

    @Operation(summary = "Get purchases by client ID", description = "Retrieves all purchases made by a specific client")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Purchases found for client"),
            @ApiResponse(responseCode = "404", description = "No purchases found for client")
    })
    @GetMapping("/client/{idClient}")
    public ResponseEntity<List<Purchase>> getByClient(
            @Parameter(description = "ID of the client to retrieve purchases for")
            @PathVariable("idClient") String clientId) {
        return purchaseService.getByClient(clientId).map(
                purchases -> new ResponseEntity<>(purchases, HttpStatus.OK)
        ).orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @Operation(summary = "Save new purchase", description = "Creates a new purchase record")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Purchase successfully created")
    })
    @PostMapping("/save")
    public ResponseEntity<Purchase> save(
            @Parameter(description = "Purchase object to be created")
            @RequestBody Purchase purchase) {
        logger.info("Received purchase: {}", purchase);
        return new ResponseEntity<>(purchaseService.save(purchase), HttpStatus.CREATED);
    }
}