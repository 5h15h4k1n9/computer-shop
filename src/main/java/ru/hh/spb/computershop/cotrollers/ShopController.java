package ru.hh.spb.computershop.cotrollers;

import org.jetbrains.annotations.Nullable;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import ru.hh.spb.computershop.entities.Product;
import ru.hh.spb.computershop.enums.ComputerType;
import ru.hh.spb.computershop.enums.Manufacturer;
import ru.hh.spb.computershop.enums.ResponseType;
import ru.hh.spb.computershop.responses.ErrorResponse;
import ru.hh.spb.computershop.responses.ShopResponse;
import ru.hh.spb.computershop.responses.SuccessfulResponse;
import ru.hh.spb.computershop.services.ProductService;

import java.util.List;
import java.util.Map;
import java.util.Objects;

@RestController
@RequestMapping("/v1/computer-shop/api")
public class ShopController {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private ProductService productService;

    @GetMapping("/products/all")
    public ResponseEntity<Map<String, Object>> getAllProducts() {
        logger.info("Client requested all products");

        List<Product> products = productService.getAllProducts();
        int count = products.size();

        logger.info("Returning {} products", count);
        Map<String, Object> response = Map.of(
                "count", count,
                "products", products
        );

        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping("products/product")
    public ResponseEntity<Object> getProductById(@RequestParam Long id) {
        logger.info("Client requested product with id {}", id);

        Product product = productService.getProduct(id);

        if (Objects.isNull(product)) {
            logger.info("Product with id {} not found", id);

            ErrorResponse errorResponse = new ErrorResponse(
                    "Product with id " + id + " not found",
                    HttpStatus.NOT_FOUND,
                    String.valueOf(System.currentTimeMillis())
            );
            return new ResponseEntity<>(errorResponse, HttpStatus.NOT_FOUND);
        }

        SuccessfulResponse successfulResponse = new SuccessfulResponse(
                ResponseType.PRODUCT,
                product,
                String.valueOf(System.currentTimeMillis())
        );

        logger.info("Returning product with id {}", id);
        return new ResponseEntity<>(successfulResponse, HttpStatus.OK);
    }

    @PostMapping("/products/product/add/computer")
    public ResponseEntity<ShopResponse> addComputer(
            @RequestParam String serialNumber,
            @RequestParam String manufacturer,
            @RequestParam Long cost,
            @RequestParam Long count,
            @RequestParam String type
    ) {
        logger.info("Client requested to add computer with serial number {}", serialNumber);

        ResponseEntity<ShopResponse> manufacturerError = checkManufacturer(manufacturer);
        if (Objects.nonNull(manufacturerError)) {
            return manufacturerError;
        }
        Manufacturer manufacturerEnum = Manufacturer.valueOf(manufacturer);

        try {
            ComputerType computerType = ComputerType.valueOf(type);
            Product product = productService.saveProduct(serialNumber, manufacturerEnum, cost, count, computerType);

            ResponseEntity<ShopResponse> errorResponse = checkProductIsExist(product, serialNumber);
            if (Objects.nonNull(errorResponse)) {
                return errorResponse;
            }

            SuccessfulResponse successfulResponse = new SuccessfulResponse(
                    ResponseType.PRODUCT,
                    product,
                    String.valueOf(System.currentTimeMillis())
            );

            logger.info("Successfully added computer with serial number {}", serialNumber);
            return new ResponseEntity<>(successfulResponse, HttpStatus.OK);
        } catch (IllegalArgumentException e) {
            logger.warn("Computer type {} is not valid", type);

            ErrorResponse errorResponse = new ErrorResponse(
                    "Computer type " + type + " is not valid",
                    HttpStatus.BAD_REQUEST,
                    String.valueOf(System.currentTimeMillis())
            );
            return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
        }
    }

    @Nullable
    private ResponseEntity<ShopResponse> checkProductIsExist(Product product, String serialNumber) {
        if (Objects.isNull(product)) {
            logger.info("Product with serial number {} already exist", serialNumber);

            ErrorResponse errorResponse = new ErrorResponse(
                    "Product with serial number " + serialNumber + " already exist",
                    HttpStatus.BAD_REQUEST,
                    String.valueOf(System.currentTimeMillis())
            );
            return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
        }

        return null;
    }

    @Nullable
    private ResponseEntity<ShopResponse> checkManufacturer(String manufacturerString) {
        try {
            Manufacturer manufacturer = Manufacturer.valueOf(manufacturerString);
        } catch (IllegalArgumentException e) {
            logger.warn("Manufacturer {} is not valid", manufacturerString);

            ErrorResponse errorResponse = new ErrorResponse(
                    "Manufacturer " + manufacturerString + " is not valid",
                    HttpStatus.BAD_REQUEST,
                    String.valueOf(System.currentTimeMillis())
            );
            return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
        }

        return null;
    }
}
