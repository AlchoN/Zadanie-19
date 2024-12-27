package com.example.location_service.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.location_service.model.Person;

@Repository
public interface PersonRepository extends JpaRepository<Person, Long> {
}
