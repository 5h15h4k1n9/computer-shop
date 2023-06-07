package ru.hh.spb.computershop.entities;

import jakarta.persistence.Entity;

import ru.hh.spb.computershop.enums.ComputerType;
import ru.hh.spb.computershop.enums.Manufacturer;
import ru.hh.spb.computershop.enums.ProductType;

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
            Long cost,
            Long count,
            ComputerType computerType
    ) {
        super(serialNumber, manufacturer, cost, count);

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
