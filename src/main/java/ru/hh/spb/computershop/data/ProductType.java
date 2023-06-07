package ru.hh.spb.computershop.data;

public enum ProductType implements java.io.Serializable{

    UNKNOWN,
    COMPUTER,
    NOTEBOOK,
    MONITOR,
    HDD;

    public static ProductType fromValue(String value) {
        for (ProductType type : values()) {
            if (type.name().equalsIgnoreCase(value)) {
                return type;
            }
        }
        return UNKNOWN;
    }
}
