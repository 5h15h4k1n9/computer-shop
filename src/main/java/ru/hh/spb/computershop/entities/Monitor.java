package ru.hh.spb.computershop.entities;

import jakarta.persistence.Entity;
import ru.hh.spb.computershop.visitor.ProductVisitor;

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
            Long price,
            Long count,
            Byte diagonal
    ) {
        super(serialNumber, manufacturer, price, count);

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

    public void setDiagonal(Byte diagonal) {
        this.diagonal = diagonal;
    }

    public void accept(ProductVisitor visitor, String diagonal) {
        visitor.visitMonitor(this, diagonal);
    }
}
