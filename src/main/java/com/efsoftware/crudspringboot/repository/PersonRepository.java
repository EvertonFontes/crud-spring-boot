package com.efsoftware.crudspringboot.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.efsoftware.crudspringboot.model.Person;

@Repository
public interface PersonRepository extends JpaRepository<Person, Integer>{  

}
