package ru.hh.spb.computershop.controllers;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
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

    @ApiResponse(code = 200, message = "version", examples = @io.swagger.annotations.Example(
            value = {@io.swagger.annotations.ExampleProperty(value = "1.0.0", mediaType = MediaType.TEXT_PLAIN_VALUE)}
    ))
    @ApiOperation(
            value = "Get version",
            notes = "Returns version"
    )
    @GetMapping(value = "", produces = MediaType.TEXT_PLAIN_VALUE)
    public ResponseEntity<String> getVersion() {
        logger.info("Getting version");
        return ResponseEntity.ok(version);
    }
}
