package ru.hh.spb.computershop.entities;

import jakarta.persistence.Entity;

@Entity
public class Monitor extends Product {

    Byte diagonal;

    public Monitor() {
        super();

        this.diagonal = 0;
    }

    public Monitor(
            String serialNumber,
            Manufacturer manufacturer,
            Long cost,
            Long count,
            Byte diagonal
    ) {
        super(serialNumber, manufacturer, cost, count);

        this.diagonal = diagonal;
    }

    public Byte getDiagonal() {
        return diagonal;
    }

    public String toString() {
        String superString = super.toString();
        return "Monitor{" +
                superString +
                ", diagonal=" + diagonal +
                '}';
    }
}
