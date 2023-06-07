package ru.hh.spb.computershop.data;

public enum Manufacturer implements java.io.Serializable {

    UNKNOWN,
    ASUS,
    ACER,
    APPLE,
    SAMSUNG,
    WESTERN_DIGITAL,
    SEAGATE,
    TOSHIBA,
    AOC,
    LG,
    SONY,
    MICROSOFT;

    public static Manufacturer fromValue(String value) {
        for (Manufacturer manufacturer : values()) {
            if (manufacturer.name().equalsIgnoreCase(value)) {
                return manufacturer;
            }
        }
        return UNKNOWN;
    }
}
