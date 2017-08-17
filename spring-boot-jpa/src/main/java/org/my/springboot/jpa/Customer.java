package org.my.springboot.jpa;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class Customer {

    @Id
    @GeneratedValue
    Long id;

    String name;

    String email;

    @Column(name = "CREATED_DATE")
    Date date;

    //getters and setters, contructors
}