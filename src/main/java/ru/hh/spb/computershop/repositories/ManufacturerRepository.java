package ru.hh.spb.computershop.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import ru.hh.spb.computershop.entities.Manufacturer;

@Repository
public interface ManufacturerRepository extends CrudRepository<Manufacturer, String> {

    Manufacturer getManufacturerByName(String name);

    Manufacturer getManufacturerById(Long id);
}
