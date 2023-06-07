package ru.hh.spb.computershop.entities;

import jakarta.persistence.*;

import ru.hh.spb.computershop.enums.Manufacturer;
import ru.hh.spb.computershop.enums.ProductType;

@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name="product_type")
@Table(name="PRODUCTS")
public abstract class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "product_seq")
    Long id;

    protected ProductType type;

    @Column(unique = true, nullable = false, length = 20)
    String serialNumber;

    Long price;

    Long count;

    Manufacturer manufacturer;

    public Product() {
        this.serialNumber = "";
        this.manufacturer = Manufacturer.UNKNOWN;
        this.price = 0L;
        this.count = 0L;
    }

    public Product(String serialNumber, Manufacturer manufacturer, Long cost, Long count) {
        this.serialNumber = serialNumber;
        this.manufacturer = manufacturer;
        this.price = cost;
        this.count = count;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public ProductType getType() {
        return type;
    }

    public String getSerialNumber() {
        return serialNumber;
    }

    public Long getPrice() {
        return price;
    }

    public Long getCount() {
        return count;
    }

    public Manufacturer getManufacturer() {
        return manufacturer;
    }

    public String toString() {
        return "Product(" +
                "id=" + id +
                ", serialNumber=" + serialNumber +
                ", manufacturer=" + manufacturer +
                ", price=" + price +
                ", count=" + count +
                ", type=" + type +
                ")";
    }
}