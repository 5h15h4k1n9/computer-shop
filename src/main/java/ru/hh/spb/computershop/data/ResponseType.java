package ru.hh.spb.computershop.data;

public enum ResponseType {

    OBJECT("object"), LISTING("listing");

    private final String type;

    ResponseType(String type) {
        this.type = type;
    }

    public String getType() {
        return type;
    }
}
