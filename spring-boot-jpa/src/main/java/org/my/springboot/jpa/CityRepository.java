package org.my.springboot.jpa;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

public interface CityRepository extends CrudRepository<City, Long> {
    List<City> findByCity(String city);
}
