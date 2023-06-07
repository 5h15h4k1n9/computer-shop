package ru.hh.spb.computershop.data;

public enum NotebookSize implements java.io.Serializable {

    UNKNOWN(0),

    THIRTEEN(13),
    FOURTEEN(14),
    FIFTEEN(15),
    SIXTEEN(16),
    SEVENTEEN(17);

    private final int value;

    public int getValue() {
        return value;
    }

    NotebookSize(int value) {
        this.value = value;
    }

    public static NotebookSize fromValue(int value) {
        for (NotebookSize size : values()) {
            if (size.value == value) {
                return size;
            }
        }
        return UNKNOWN;
    }
}
