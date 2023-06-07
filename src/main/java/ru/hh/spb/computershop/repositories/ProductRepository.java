package ru.hh.spb.computershop.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import ru.hh.spb.computershop.entities.Manufacturer;
import ru.hh.spb.computershop.entities.Product;
import ru.hh.spb.computershop.data.ProductType;

import java.util.List;

@Repository
public
interface ProductRepository extends CrudRepository<Product, Long> {

    Product getProductById(Long id);

    Product getProductsBySerialNumber(String serialNumber);

    List<Product> getProductsByType(ProductType type);

    List<Product> getProductsByManufacturer(Manufacturer manufacturer);
}
