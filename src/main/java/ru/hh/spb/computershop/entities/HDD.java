package ru.hh.spb.computershop.entities;

import javax.persistence.Entity;

import ru.hh.spb.computershop.data.ProductType;
import ru.hh.spb.computershop.visitor.ProductVisitor;

@Entity
public class HDD extends Product {

    Long volume;

    public HDD() {
        super();

        this.type = ProductType.HDD;
        this.volume = 0L;
    }

    public HDD(
            String serialNumber,
            Manufacturer manufacturer,
            Long price,
            Long count,
            Long volume
    ) {
        super(serialNumber, manufacturer, price, count);

        this.type = ProductType.HDD;
        this.volume = volume;
    }

    public Long getVolume() {
        return volume;
    }

    public String toString() {
        String superString = super.toString();
        return "HDD{" +
                superString +
                ", volume=" + volume +
                '}';
    }

    public void setVolume(Long volume) {
        this.volume = volume;
    }

    public void accept(ProductVisitor visitor, String volume) {
        visitor.visitHDD(this, volume);
    }
}
