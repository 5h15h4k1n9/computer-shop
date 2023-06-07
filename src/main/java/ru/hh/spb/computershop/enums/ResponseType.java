package ru.hh.spb.computershop.enums;

public enum ResponseType {

    PRODUCT("product"), PRODUCT_LIST("product_list");

    private final String type;

    ResponseType(String type) {
        this.type = type;
    }

    public String getType() {
        return type;
    }
}
