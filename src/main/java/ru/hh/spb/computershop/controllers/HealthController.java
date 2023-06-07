package ru.hh.spb.computershop.controllers;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/health")
public class HealthController {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @ApiOperation(
            value = "Health check",
            notes = "Returns OK if service is alive"
    )
    @ApiResponse(code = 200, message = "OK",
            examples = @io.swagger.annotations.Example(
                    value = {@io.swagger.annotations.ExampleProperty(value = "OK", mediaType = MediaType.TEXT_PLAIN_VALUE)}
            )
    )
    @GetMapping(value = "", produces = MediaType.TEXT_PLAIN_VALUE)
    public ResponseEntity<String> health() {
        logger.info("Health check");
        return ResponseEntity.ok("OK");
    }
}
