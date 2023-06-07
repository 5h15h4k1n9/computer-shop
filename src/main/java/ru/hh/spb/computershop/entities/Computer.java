package ru.hh.spb.computershop.entities;

import jakarta.persistence.Entity;

import ru.hh.spb.computershop.data.ComputerType;
import ru.hh.spb.computershop.data.ProductType;

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
}
