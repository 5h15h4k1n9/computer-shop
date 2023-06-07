package ru.hh.spb.computershop.cotrollers;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import ru.hh.spb.computershop.entities.Manufacturer;
import ru.hh.spb.computershop.entities.Product;
import ru.hh.spb.computershop.data.*;
import ru.hh.spb.computershop.responses.ErrorResponse;
import ru.hh.spb.computershop.responses.ShopResponse;
import ru.hh.spb.computershop.responses.SuccessfulResponse;
import ru.hh.spb.computershop.services.ManufacturerService;
import ru.hh.spb.computershop.services.ProductService;
import ru.hh.spb.computershop.visitor.UpdateProductVisitor;

import java.util.List;
import java.util.Map;
import java.util.Objects;

@RestController
@RequestMapping("/v1/computer-shop/api/products")
public class ProductController {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private ProductService productService;

    @Autowired
    private ManufacturerService manufacturerService;

    @GetMapping("/all")
    public ResponseEntity<ShopResponse> getAllProducts() {
        logger.info("Client requested all products");

        List<Product> products = productService.getAllProducts();
        int count = products.size();

        logger.info("Returning {} products", count);
        Map<String, Object> data = Map.of(
                "count", count,
                "products", products
        );

        ShopResponse response = new SuccessfulResponse(
                ResponseType.LISTING,
                data,
                String.valueOf(System.currentTimeMillis())
        );

        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping("/all/type/{productType}")
    public ResponseEntity<ShopResponse> getAllProductsByType(@PathVariable String productType) {
        logger.info("Client requested all products with type {}", productType);

        ProductType type = ProductType.fromValue(productType);
        List<Product> products = productService.getProductsByType(type);
        int count = products.size();

        logger.info("Returning {} products with type {}", count, productType);
        Map<String, Object> data = Map.of(
                "count", count,
                "products", products
        );

        ShopResponse response = new SuccessfulResponse(
                ResponseType.LISTING,
                data,
                String.valueOf(System.currentTimeMillis())
        );

        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping("/all/manufacturer/{manufacturerName}")
    public ResponseEntity<ShopResponse> getAllProductsByManufacturer(@PathVariable String manufacturerName) {
        logger.info("Client requested all products with manufacturer {}", manufacturerName);

        Manufacturer manufacturer = manufacturerService.getManufacturerByName(manufacturerName);
        if (Objects.isNull(manufacturer)) {
            logger.info("Manufacturer {} not found", manufacturerName);

            ErrorResponse errorResponse = new ErrorResponse(
                    "Manufacturer " + manufacturerName + " not found",
                    HttpStatus.NOT_FOUND,
                    String.valueOf(System.currentTimeMillis())
            );

            return new ResponseEntity<>(errorResponse, HttpStatus.NOT_FOUND);
        }

        List<Product> products = productService.getProductsByManufacturer(manufacturer);
        int count = products.size();

        logger.info("Returning {} products with manufacturer {}", count, manufacturerName);
        Map<String, Object> data = Map.of(
                "count", count,
                "products", products
        );

        ShopResponse response = new SuccessfulResponse(
                ResponseType.LISTING,
                data,
                String.valueOf(System.currentTimeMillis())
        );

        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping("/product/id/{id}")
    public ResponseEntity<Object> getProductById(@PathVariable Long id) {
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
                ResponseType.OBJECT,
                product,
                String.valueOf(System.currentTimeMillis())
        );

        logger.info("Returning product with id {}", id);
        return new ResponseEntity<>(successfulResponse, HttpStatus.OK);
    }

    @GetMapping("/product/serial-number/{serialNumber}")
    public ResponseEntity<Object> getProductBySerialNumber(@PathVariable String serialNumber) {
        logger.info("Client requested product with serial number {}", serialNumber);

        Product product = productService.getProduct(serialNumber);

        if (Objects.isNull(product)) {
            logger.info("Product with serial number {} not found", serialNumber);

            ErrorResponse errorResponse = new ErrorResponse(
                    "Product with serial number " + serialNumber + " not found",
                    HttpStatus.NOT_FOUND,
                    String.valueOf(System.currentTimeMillis())
            );
            return new ResponseEntity<>(errorResponse, HttpStatus.NOT_FOUND);
        }

        SuccessfulResponse successfulResponse = new SuccessfulResponse(
                ResponseType.OBJECT,
                product,
                String.valueOf(System.currentTimeMillis())
        );

        logger.info("Returning product with serial number {}", serialNumber);
        return new ResponseEntity<>(successfulResponse, HttpStatus.OK);
    }


    @PostMapping("/product/add/{productType}")
    public ResponseEntity<ShopResponse> addComputer(
            @NotNull @RequestParam Map<String, String> params,
            @PathVariable String productType
    ) {
        logger.info("Client requested to add product with type {}", productType);

        ResponseEntity<ShopResponse> response;

        String serialNumber = params.get(ResponseParameter.SERIAL_NUMBER);
        response = checkParameter(serialNumber, ResponseParameter.SERIAL_NUMBER);
        if (Objects.nonNull(response)) {
            return response;
        }

        String priceString = params.get(ResponseParameter.PRICE);
        response = checkParameter(priceString, ResponseParameter.PRICE);
        if (Objects.nonNull(response)) {
            return response;
        }
        if (!priceString.matches("\\d+")) {
            logger.info("Cost {} is not a number", priceString);

            ErrorResponse errorResponse = new ErrorResponse(
                    "Cost " + priceString + " is not a number",
                    HttpStatus.BAD_REQUEST,
                    String.valueOf(System.currentTimeMillis())
            );
            return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
        }

        String countString = params.get(ResponseParameter.COUNT);
        response = checkParameter(countString, ResponseParameter.COUNT);
        if (Objects.nonNull(response)) {
            return response;
        }
        if (!countString.matches("\\d+")) {
            logger.info("Count {} is not a number", countString);

            ErrorResponse errorResponse = new ErrorResponse(
                    "Count " + countString + " is not a number",
                    HttpStatus.BAD_REQUEST,
                    String.valueOf(System.currentTimeMillis())
            );
            return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
        }

        String manufacturerString = params.get(ResponseParameter.MANUFACTURER);
        response = checkParameter(manufacturerString, ResponseParameter.MANUFACTURER);
        if (Objects.nonNull(response)) {
            return response;
        }


        Long price = Long.parseLong(priceString);
        Long count = Long.parseLong(countString);
        Manufacturer manufacturer = manufacturerService.getManufacturerByName(manufacturerString);

        Product product;
        switch (ProductType.fromValue(productType.toUpperCase())) {
            case COMPUTER -> {
                String computerTypeString = params.get(ResponseParameter.COMPUTER_TYPE);
                response = checkParameter(computerTypeString, ResponseParameter.COMPUTER_TYPE);
                if (Objects.nonNull(response)) {
                    return response;
                }

                ComputerType computerType = ComputerType.fromValue(computerTypeString);
                product = productService.saveProduct(serialNumber, manufacturer, price, count, computerType);
            }
            case NOTEBOOK -> {
                String sizeString = params.get(ResponseParameter.NOTEBOOK_SIZE);
                response = checkParameter(sizeString, ResponseParameter.NOTEBOOK_SIZE);
                if (Objects.nonNull(response)) {
                    return response;
                }

                if (!sizeString.matches("\\d{1,2}")) {
                    logger.warn("Invalid notebook size {}", sizeString);

                    ErrorResponse errorResponse = new ErrorResponse(
                            "Invalid notebook size " + sizeString,
                            HttpStatus.BAD_REQUEST,
                            String.valueOf(System.currentTimeMillis())
                    );

                    return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
                }

                NotebookSize notebookSize = NotebookSize.fromValue(Integer.parseInt(sizeString));
                product = productService.saveProduct(serialNumber, manufacturer, price, count, notebookSize);
            }
            case MONITOR -> {
                String diagonalString = params.get(ResponseParameter.DIAGONAL);
                response = checkParameter(diagonalString, ResponseParameter.DIAGONAL);
                if (Objects.nonNull(response)) {
                    return response;
                }

                if (!diagonalString.matches("\\d{1,2}")) {
                    logger.warn("Invalid monitor diagonal {}", diagonalString);

                    ErrorResponse errorResponse = new ErrorResponse(
                            "Invalid monitor diagonal " + diagonalString,
                            HttpStatus.BAD_REQUEST,
                            String.valueOf(System.currentTimeMillis())
                    );

                    return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
                }

                Byte diagonal = Byte.parseByte(diagonalString);
                product = productService.saveProduct(serialNumber, manufacturer, price, count, diagonal);
            }
            case HDD -> {
                String volumeString = params.get(ResponseParameter.VOLUME);
                response = checkParameter(volumeString, ResponseParameter.VOLUME);
                if (Objects.nonNull(response)) {
                    return response;
                }

                if (!volumeString.matches("\\d+")) {
                    logger.warn("Invalid HDD volume {}", volumeString);

                    ErrorResponse errorResponse = new ErrorResponse(
                            "Invalid HDD volume " + volumeString,
                            HttpStatus.BAD_REQUEST,
                            String.valueOf(System.currentTimeMillis())
                    );

                    return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
                }

                Long volume = Long.parseLong(volumeString);
                product = productService.saveProduct(serialNumber, manufacturer, price, count, volume);
            }
            default -> {
                logger.warn("Unknown product type {}", productType);

                ErrorResponse errorResponse = new ErrorResponse(
                        "Unknown product type " + productType,
                        HttpStatus.BAD_REQUEST,
                        String.valueOf(System.currentTimeMillis())
                );

                return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
            }
        }

        if (Objects.isNull(product)) {
            logger.info("Product with serial number {} already exist", serialNumber);

            ErrorResponse errorResponse = new ErrorResponse(
                    "Product with serial number " + serialNumber + " already exist",
                    HttpStatus.BAD_REQUEST,
                    String.valueOf(System.currentTimeMillis())
            );
            return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
        }

        response = new ResponseEntity<>(
                new SuccessfulResponse(ResponseType.OBJECT, product, String.valueOf(System.currentTimeMillis())),
                HttpStatus.OK
        );

        logger.info("Successfully added product with serial number {}", serialNumber);
        return response;
    }

    @PutMapping("/product/update/{serialNumber}")
    public ResponseEntity<ShopResponse> updateProduct(
            @RequestParam Map<String, String> params,
            @PathVariable String serialNumber
    ) {
        logger.info("Client requested to update product with serial number {}", serialNumber);

        Product product = productService.getProduct(serialNumber);
        if (Objects.isNull(product)) {
            logger.info("Product with serial number {} not found", serialNumber);

            ErrorResponse errorResponse = new ErrorResponse(
                    "Product with serial number " + serialNumber + " not found",
                    HttpStatus.NOT_FOUND,
                    String.valueOf(System.currentTimeMillis())
            );
            return new ResponseEntity<>(errorResponse, HttpStatus.NOT_FOUND);
        }

        boolean isUpdated = false;

        String costString = params.get(ResponseParameter.PRICE);
        if (Objects.nonNull(costString)) {
            if (!costString.matches("\\d+")) {
                logger.warn("Invalid cost {}", costString);

                ErrorResponse errorResponse = new ErrorResponse(
                        "Invalid cost " + costString,
                        HttpStatus.BAD_REQUEST,
                        String.valueOf(System.currentTimeMillis())
                );

                return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
            }

            Long cost = Long.parseLong(costString);
            product.setCost(cost);
            isUpdated = true;
        }

        String countString = params.get(ResponseParameter.COUNT);
        if (Objects.nonNull(countString)) {
            if (!countString.matches("\\d+")) {
                logger.warn("Invalid count {}", countString);

                ErrorResponse errorResponse = new ErrorResponse(
                        "Invalid count " + countString,
                        HttpStatus.BAD_REQUEST,
                        String.valueOf(System.currentTimeMillis())
                );

                return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
            }

            Long count = Long.parseLong(countString);
            product.setCount(count);
            isUpdated = true;
        }

        String manufacturerString = params.get(ResponseParameter.MANUFACTURER);
        if (Objects.nonNull(manufacturerString)) {
            Manufacturer manufacturer = manufacturerService.getManufacturerByName(manufacturerString);
            if (Objects.isNull(manufacturer)) {
                logger.info("Manufacturer with name {} not found", manufacturerString);

                ErrorResponse errorResponse = new ErrorResponse(
                        "Manufacturer with name " + manufacturerString + " not found",
                        HttpStatus.NOT_FOUND,
                        String.valueOf(System.currentTimeMillis())
                );
                return new ResponseEntity<>(errorResponse, HttpStatus.NOT_FOUND);
            }

            product.setManufacturer(manufacturer);
            isUpdated = true;
        }

        String serialNumberString = params.get(ResponseParameter.SERIAL_NUMBER);
        if (Objects.nonNull(serialNumberString)) {
            product.setSerialNumber(serialNumberString);
            isUpdated = true;
        }

        String computerTypeString = params.get(ResponseParameter.COMPUTER_TYPE);
        if (Objects.nonNull(computerTypeString)) {
            product.accept(new UpdateProductVisitor(), computerTypeString);
            isUpdated = true;

            productService.updateProduct(product);
            logger.info("Product with serial number {} successfully updated", serialNumber);

            return new ResponseEntity<>(
                    new SuccessfulResponse(ResponseType.OBJECT, product, String.valueOf(System.currentTimeMillis())),
                    HttpStatus.OK
            );
        }

        String sizeString = params.get(ResponseParameter.NOTEBOOK_SIZE);
        if (Objects.nonNull(sizeString)) {
            if (!sizeString.matches("\\d{1,2}")) {
                logger.warn("Invalid notebook size {}", sizeString);

                ErrorResponse errorResponse = new ErrorResponse(
                        "Invalid notebook size " + sizeString,
                        HttpStatus.BAD_REQUEST,
                        String.valueOf(System.currentTimeMillis())
                );

                return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
            }

            product.accept(new UpdateProductVisitor(), sizeString);
            isUpdated = true;

            productService.updateProduct(product);
            logger.info("Product with serial number {} successfully updated", serialNumber);

            return new ResponseEntity<>(
                    new SuccessfulResponse(ResponseType.OBJECT, product, String.valueOf(System.currentTimeMillis())),
                    HttpStatus.OK
            );
        }

        String diagonalString = params.get(ResponseParameter.DIAGONAL);
        if (Objects.nonNull(diagonalString)) {
            if (!diagonalString.matches("\\d{1,2}")) {
                logger.warn("Invalid diagonal {}", diagonalString);

                ErrorResponse errorResponse = new ErrorResponse(
                        "Invalid diagonal " + diagonalString,
                        HttpStatus.BAD_REQUEST,
                        String.valueOf(System.currentTimeMillis())
                );

                return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
            }

            product.accept(new UpdateProductVisitor(), diagonalString);
            isUpdated = true;

            productService.updateProduct(product);
            logger.info("Product with serial number {} successfully updated", serialNumber);

            return new ResponseEntity<>(
                    new SuccessfulResponse(ResponseType.OBJECT, product, String.valueOf(System.currentTimeMillis())),
                    HttpStatus.OK
            );
        }

        String volumeString = params.get(ResponseParameter.VOLUME);
        if (Objects.nonNull(volumeString)) {
            if (!volumeString.matches("\\d+")) {
                logger.warn("Invalid volume {}", volumeString);

                ErrorResponse errorResponse = new ErrorResponse(
                        "Invalid volume " + volumeString,
                        HttpStatus.BAD_REQUEST,
                        String.valueOf(System.currentTimeMillis())
                );

                return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
            }

            product.accept(new UpdateProductVisitor(), volumeString);
            isUpdated = true;

            productService.updateProduct(product);
            logger.info("Product with serial number {} successfully updated", serialNumber);

            return new ResponseEntity<>(
                    new SuccessfulResponse(ResponseType.OBJECT, product, String.valueOf(System.currentTimeMillis())),
                    HttpStatus.OK
            );
        }

        if (isUpdated) {
            productService.updateProduct(product);
            logger.info("Product with serial number {} successfully updated", serialNumber);

            return new ResponseEntity<>(
                    new SuccessfulResponse(ResponseType.OBJECT, product, String.valueOf(System.currentTimeMillis())),
                    HttpStatus.OK
            );
        }

        logger.info("Product with serial number {} not updated", serialNumber);
        ErrorResponse errorResponse = new ErrorResponse(
                "Product with serial number " + serialNumber + " not updated",
                HttpStatus.BAD_REQUEST,
                String.valueOf(System.currentTimeMillis())
        );
        return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
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
    private ResponseEntity<ShopResponse> checkParameter(String parameter, String parameterName) {
        if (Objects.isNull(parameter)) {
            logger.warn("Request parameter '{}' is not specified", parameterName);

            ErrorResponse errorResponse = new ErrorResponse(
                    "Request parameter '" + parameterName + "' is not specified",
                    HttpStatus.BAD_REQUEST,
                    String.valueOf(System.currentTimeMillis())
            );

            return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
        }

        return null;
    }
}
