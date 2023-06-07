package ru.hh.spb.computershop.controllers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import ru.hh.spb.computershop.data.ResponseType;
import ru.hh.spb.computershop.entities.Manufacturer;
import ru.hh.spb.computershop.responses.ErrorResponse;
import ru.hh.spb.computershop.responses.ShopResponse;
import ru.hh.spb.computershop.responses.SuccessfulResponse;
import ru.hh.spb.computershop.services.ManufacturerService;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/v1/computer-shop/manufacturers")
public class ManufacturerController {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    ManufacturerService manufacturerService;

    @GetMapping("/all")
    public ResponseEntity<ShopResponse> getAllManufacturers() {
        logger.info("Client requested all manufacturers");

        List<Manufacturer> manufacturers = manufacturerService.getAllManufacturers();
        Map<String, Object> data = Map.of(
                "manufacturers", manufacturers,
                "count", manufacturers.size()
        );

        ShopResponse response = new SuccessfulResponse(
                ResponseType.LISTING,
                data,
                String.valueOf(System.currentTimeMillis())
        );

        return ResponseEntity.ok(response);
    }

    @GetMapping("/add")
    public ResponseEntity<ShopResponse> addManufacturer(String name) {
        logger.info("Client requested to add manufacturer with name '{}'", name);

        Manufacturer manufacturer = manufacturerService.saveManufacturer(name);
        if (manufacturer == null) {
            logger.info("Manufacturer with name '{}' already exists", name);
            ShopResponse response = new ErrorResponse(
                    "Manufacturer with name '" + name + "' already exists",
                    HttpStatus.BAD_REQUEST,
                    String.valueOf(System.currentTimeMillis())
            );

            return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
        }

        ShopResponse response = new SuccessfulResponse(
                ResponseType.OBJECT,
                manufacturer,
                String.valueOf(System.currentTimeMillis())
        );

        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/delete")
    public ResponseEntity<ShopResponse> deleteManufacturer(String name) {
        logger.info("Client requested to delete manufacturer with name '{}'", name);

        Manufacturer manufacturer = manufacturerService.deleteManufacturer(name);
        if (manufacturer == null) {
            logger.info("Manufacturer with name '{}' doesn't exist", name);
            ShopResponse response = new ErrorResponse(
                    "Manufacturer with name '" + name + "' doesn't exist",
                    HttpStatus.BAD_REQUEST,
                    String.valueOf(System.currentTimeMillis())
            );

            return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
        }

        ShopResponse response = new SuccessfulResponse(
                ResponseType.OBJECT,
                manufacturer,
                String.valueOf(System.currentTimeMillis())
        );

        return ResponseEntity.ok(response);
    }
}
