package ru.hh.spb.computershop.entities;

import javax.persistence.Entity;

import ru.hh.spb.computershop.data.ComputerType;
import ru.hh.spb.computershop.data.ProductType;
import ru.hh.spb.computershop.visitor.ProductVisitor;

@Entity
public class Computer extends Product {

    ComputerType computerType;

    public Computer() {
        super();

        this.type = ProductType.COMPUTER;
        this.computerType = ComputerType.UNKNOWN;
    }

    public Computer(
            String serialNumber,
            Manufacturer manufacturer,
            Long price,
            Long count,
            ComputerType computerType
    ) {
        super(serialNumber, manufacturer, price, count);

        this.type = ProductType.COMPUTER;
        this.computerType = computerType;
    }

    public ComputerType getComputerType() {
        return computerType;
    }

    public String toString() {
        String superString = super.toString();
        return "Computer{" +
                superString +
                ", computerType=" + computerType +
                '}';
    }

    public void setComputerType(ComputerType type) {
        this.computerType = type;
    }

    public void accept(ProductVisitor visitor, String computerType) {
        visitor.visitComputer(this, computerType);
    }
}
