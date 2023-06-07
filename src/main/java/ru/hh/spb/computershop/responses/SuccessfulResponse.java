package ru.hh.spb.computershop.responses;

import ru.hh.spb.computershop.enums.ResponseType;

public record SuccessfulResponse(ResponseType type, Object data, String timestamp) implements ShopResponse {
}
