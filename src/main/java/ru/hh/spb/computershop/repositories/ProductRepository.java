package ru.hh.spb.computershop.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import ru.hh.spb.computershop.entities.Product;
import ru.hh.spb.computershop.enums.ProductType;

import java.util.List;

@Repository
public
interface ProductRepository extends CrudRepository<Product, Long> {

    Product getProductById(Long id);

    List<Product> getProductsByType(ProductType type);
}
