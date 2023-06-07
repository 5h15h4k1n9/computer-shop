package ru.hh.spb.computershop.services;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.hh.spb.computershop.entities.Manufacturer;
import ru.hh.spb.computershop.repositories.ManufacturerRepository;

import java.util.List;

@Service
public class ManufacturerService {

    private final Logger logger = LoggerFactory.getLogger(ManufacturerService.class);

    @Autowired
    ManufacturerRepository manufacturerRepository;

    public Manufacturer saveManufacturer(String name) {
        logger.info("Adding manufacturer with name: {}", name);
        if (isManufacturerExists(name)) {
            logger.info("Manufacturer with name: {} already exists", name);
            return null;
        }

        return manufacturerRepository.save(new Manufacturer(name));
    }

    public Manufacturer getManufacturerByName(String name) {
        logger.info("Getting manufacturer with name '{}'", name);
        return manufacturerRepository.getManufacturerByName(name);
    }

    public List<Manufacturer> getAllManufacturers() {
        logger.info("Getting all manufacturers");
        return (List<Manufacturer>) manufacturerRepository.findAll();
    }

    private boolean isManufacturerExists(String name) {
        logger.info("Checking if manufacturer with name: {} exists", name);
        Iterable<Manufacturer> manufacturers = manufacturerRepository.findAll();
        for (Manufacturer manufacturer : manufacturers) {
            if (manufacturer.getName().equalsIgnoreCase(name)) {
                return true;
            }
        }
        return false;
    }
}
