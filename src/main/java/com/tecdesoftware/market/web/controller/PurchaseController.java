package com.tecdesoftware.market.web.controller;

import com.tecdesoftware.market.domain.Purchase;
import com.tecdesoftware.market.domain.service.PurchaseService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.parameters.RequestBody;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/purchases")
@Tag(name = "Purchase Controller", description = "Manage purchase transactions for clients")
public class PurchaseController {

    @Autowired
    public PurchaseService purchaseService;

    @GetMapping("/all")
    @Operation(
            summary = "Get all purchases",
            description = "Retrieve a list of all purchases made in the store"
    )
    @ApiResponse(responseCode = "200", description = "Successfully retrieved list of purchases")
    @ApiResponse(responseCode = "500", description = "Internal server error")
    public ResponseEntity<List<Purchase>> getAll() {
        return new ResponseEntity<>(purchaseService.getAll(), HttpStatus.OK);
    }

    @GetMapping("/client/{idClient}")
    @Operation(
            summary = "Get purchases by client ID",
            description = "Returns all purchases made by a specific client"
    )
    @ApiResponse(responseCode = "200", description = "Purchases found for client")
    @ApiResponse(responseCode = "404", description = "No purchases found for client")
    @ApiResponse(responseCode = "500", description = "Internal server error")
    public ResponseEntity<List<Purchase>> getByClient(
            @Parameter(description = "ID of the client whose purchases are to be retrieved", example = "5", required = true)
            @PathVariable("idClient") Integer clientId) {
        return purchaseService.getByClient(clientId)
                .map(purchases -> new ResponseEntity<>(purchases, HttpStatus.OK))
                .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @PostMapping("/save")
    @Operation(
            summary = "Save a new purchase",
            description = "Creates a new purchase and returns the saved purchase record",
            requestBody = @RequestBody(
                    required = true,
                    content = @Content(
                            examples = @ExampleObject(
                                    name = "Example Purchase",
                                    value = """
                                    {
                                        "clientId": "5",
                                        "products": [
                                            {
                                                "productId": 1,
                                                "quantity": 2
                                            },
                                            {
                                                "productId": 2,
                                                "quantity": 1
                                            }
                                        ]
                                    }
                                    """
                            )
                    )
            )
    )
    @ApiResponse(responseCode = "201", description = "Purchase created successfully")
    @ApiResponse(responseCode = "400", description = "Invalid purchase data")
    @ApiResponse(responseCode = "401", description = "Unauthorized")
    @ApiResponse(responseCode = "403", description = "Forbidden")
    @ApiResponse(responseCode = "500", description = "Internal server error")
    public ResponseEntity<Purchase> save(@org.springframework.web.bind.annotation.RequestBody Purchase purchase) {
        return new ResponseEntity<>(purchaseService.save(purchase), HttpStatus.CREATED);
    }
}
