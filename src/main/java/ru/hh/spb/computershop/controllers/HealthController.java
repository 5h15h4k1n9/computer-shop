package ru.hh.spb.computershop.controllers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/health")
public class HealthController {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @GetMapping("")
    public ResponseEntity<String> health() {
        logger.info("Health check");
        return ResponseEntity.ok("OK");
    }
}
