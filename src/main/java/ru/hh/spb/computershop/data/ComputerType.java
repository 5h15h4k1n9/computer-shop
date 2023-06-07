package ru.hh.spb.computershop.data;

public enum ComputerType implements java.io.Serializable {

    UNKNOWN,
    DESKTOP,
    NETTOP,
    MONOBLOCK;

    public static ComputerType fromValue(String value) {
        for (ComputerType type : values()) {
            if (type.name().equalsIgnoreCase(value)) {
                return type;
            }
        }
        return UNKNOWN;
    }
}
