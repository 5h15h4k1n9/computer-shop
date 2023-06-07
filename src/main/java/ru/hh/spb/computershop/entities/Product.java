package ru.hh.spb.computershop.entities;

import jakarta.persistence.*;

import ru.hh.spb.computershop.data.ProductType;
import ru.hh.spb.computershop.visitor.ProductVisitor;

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

    @ManyToOne
    @JoinColumn(name = "manufacturer_id")
    Manufacturer manufacturer;

    public Product() {
        this.serialNumber = "";
        this.manufacturer = null;
        this.price = 0L;
        this.count = 0L;
    }

    public Product(String serialNumber, Manufacturer manufacturer, Long price, Long count) {
        this.serialNumber = serialNumber;
        this.manufacturer = manufacturer;
        this.price = price;
        this.count = count;
    }

    public abstract void accept(ProductVisitor visitor, String parameter);

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

    public void setCost(Long cost) {
        this.price = cost;
    }

    public void setCount(Long count) {
        this.count = count;
    }

    public void setManufacturer(Manufacturer manufacturer) {
        this.manufacturer = manufacturer;
    }

    public void setSerialNumber(String serialNumberString) {
        this.serialNumber = serialNumberString;
    }
}