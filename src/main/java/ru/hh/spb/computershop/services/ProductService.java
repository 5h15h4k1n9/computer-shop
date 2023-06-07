package ru.hh.spb.computershop.services;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ru.hh.spb.computershop.entities.*;
import ru.hh.spb.computershop.data.ComputerType;
import ru.hh.spb.computershop.data.NotebookSize;
import ru.hh.spb.computershop.data.ProductType;
import ru.hh.spb.computershop.repositories.ProductRepository;

import java.util.List;

@Service
public class ProductService {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private ProductRepository productRepository;

    public Product saveProduct(Product product) {
        logger.info("Saving product {}", product);

        if (isProductExists(product)) {
            logger.info("Product {} already exists", product);
            return null;
        }

        return productRepository.save(product);
    }

    @Nullable
    public Product saveProduct(
            String serialNumber,
            Manufacturer manufacturer,
            Long cost, Long count,
            ComputerType type
    ) {
        Product product = new Computer(serialNumber, manufacturer, cost, count, type);
        logger.info("Saving product {}", product);

        if (isProductExists(product)) {
            logger.info("Product {} already exists", product);
            return null;
        }

        return productRepository.save(product);
    }

    @Nullable
    public Product saveProduct(
            String serialNumber,
            Manufacturer manufacturer,
            Long cost, Long count,
            NotebookSize size
    ) {
        Product product = new Notebook(serialNumber, manufacturer, cost, count, size);
        logger.info("Saving product {}", product);

        if (isProductExists(product)) {
            logger.info("Product {} already exists", product);
            return null;
        }

        return productRepository.save(product);
    }

    @Nullable
    public Product saveProduct(
            String serialNumber,
            Manufacturer manufacturer,
            Long cost, Long count,
            Byte diagonal
    ) {
        Product product = new Monitor(serialNumber, manufacturer, cost, count, diagonal);
        logger.info("Saving product {}", product);

        if (isProductExists(product)) {
            logger.info("Product {} already exists", product);
            return null;
        }

        return productRepository.save(product);
    }

    @Nullable
    public Product saveProduct(
            String serialNumber,
            Manufacturer manufacturer,
            Long cost, Long count,
            Long volume
    ) {
        Product product = new HDD(serialNumber, manufacturer, cost, count, volume);
        logger.info("Saving product {}", product);

        if (isProductExists(product)) {
            logger.info("Product {} already exists", product);
            return null;
        }

        return productRepository.save(product);
    }

    public Product updateProduct(Product product) {
        logger.info("Updating product {}", product);

        if (!isProductExists(product)) {
            logger.info("Product {} doesn't exist", product);
            return null;
        }

        return productRepository.save(product);
    }

    public Product getProduct(Long id) {
        return productRepository.findById(id).orElse(null);
    }

    public Product getProduct(String serialNumber) {
        return productRepository.getProductsBySerialNumber(serialNumber);
    }

    public List<Product> getProductsByType(ProductType type) {
        return productRepository.getProductsByType(type);
    }

    public List<Product> getProductsByManufacturer(Manufacturer manufacturer) {
        return productRepository.getProductsByManufacturer(manufacturer);
    }

    public List<Product> getAllProducts() {
        List<Product> products = (List<Product>) productRepository.findAll();
        logger.info("Found {} products", products.size());

        return products;
    }

    private boolean isProductExists(@NotNull Product product) {
        Long id = product.getId();

        Iterable<Product> products = productRepository.findAll();
        for (Product p : products) {
            if (p.getId().equals(id)) {
                return true;
            }
        }
        return false;
    }
}
