package ru.hh.spb.computershop.responses;

import org.springframework.http.HttpStatus;

public record ErrorResponse(String message, HttpStatus status, String timestamp) implements ShopResponse {
}
