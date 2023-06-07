package ru.hh.spb.computershop.entities;

import jakarta.persistence.Entity;
import ru.hh.spb.computershop.enums.Manufacturer;

@Entity
public class Monitor extends Product {

    Long diagonal;

    public Monitor() {
        super();

        this.diagonal = 0L;
    }

    public Monitor(
            String serialNumber,
            Manufacturer manufacturer,
            Long cost,
            Long count,
            Long diagonal
    ) {
        super(serialNumber, manufacturer, cost, count);

        this.diagonal = diagonal;
    }

    public Long getDiagonal() {
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
