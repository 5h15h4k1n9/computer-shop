package ru.hh.spb.computershop.controllers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/version")
public class VersionController {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Value("${app.version}")
    private String version;

    @GetMapping("")
    public ResponseEntity<String> getVersion() {
        logger.info("Getting version");
        return ResponseEntity.ok(version);
    }
}
