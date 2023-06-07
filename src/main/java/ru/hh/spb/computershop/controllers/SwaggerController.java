package ru.hh.spb.computershop.controllers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.net.URI;


@RestController
@RequestMapping("/v1/api/swagger")
public class SwaggerController {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

//    @GetMapping("")
//    public ResponseEntity<Void> swagger() {
//        logger.info("Request to swagger");
//        HttpHeaders headers = new HttpHeaders();
//        headers.setLocation(URI.create("/swagger-ui/index.html"));
//        new ResponseEntity(headers, HttpStatus.FOUND);
//    }
}
