package ru.hh.spb.computershop.entities;

import javax.persistence.*;

import java.util.List;

@Entity
@Table(name = "MANUFACTURER")
public class Manufacturer {

    @Id
    @GeneratedValue(strategy = javax.persistence.GenerationType.IDENTITY, generator = "MANUFACTURER_ID_SEQ")
    private Long id;

    @Column(unique = true, nullable = false, length = 20)
    private String name;

    @OneToMany(mappedBy = "manufacturer", cascade = CascadeType.ALL)
    private List<Product> products;

    public Manufacturer() {
    }

    public Manufacturer(String name) {
        this.name = name;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    @Override
    public String toString() {
        return "Manufacturer{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}
