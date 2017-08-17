package org.my.springboot.jpa;

import java.util.Date;
import java.util.List;

import org.springframework.data.repository.CrudRepository;

public interface CustomerRepository extends CrudRepository<Customer, Long> {
    List<Customer> findByEmail(String email);
    List<Customer> findByDate(Date date);
}
