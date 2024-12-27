package com.example.location_service.Controller;

import com.example.location_service.Repository.LocationRepository;
import com.example.location_service.Repository.PersonRepository;
import com.example.location_service.model.Location;
import com.example.location_service.model.Person;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/location")
public class LocationController {

    @Autowired
    private LocationRepository locationRepository;
    @Autowired
    private PersonRepository personRepository;

    @PostMapping
    public ResponseEntity<Location> createLocation(@RequestBody Location location) {
        return new ResponseEntity<>(locationRepository.save(location), HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<Location>> getAllLocations() {
        return ResponseEntity.ok(locationRepository.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Location> getLocationById(@PathVariable Long id) {
        Optional<Location> location = locationRepository.findById(id);
        return location.map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
    }

    @PutMapping("/{id}")
    public ResponseEntity<Location> updateLocation(@PathVariable Long id, @RequestBody Location updatedLocation) {
        Optional<Location> existingLocation = locationRepository.findById(id);
        if (existingLocation.isPresent()) {
            updatedLocation.setId(id);
            return new ResponseEntity<>(locationRepository.save(updatedLocation), HttpStatus.OK);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteLocation(@PathVariable Long id) {
        if (locationRepository.existsById(id)) {
            locationRepository.deleteById(id);
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping("/person")
    public ResponseEntity<Person> createPerson(@RequestBody Person person) {
        return new ResponseEntity<>(personRepository.save(person), HttpStatus.CREATED);
    }

    @GetMapping("/person")
    public ResponseEntity<List<Person>> getAllPersons() {
        return ResponseEntity.ok(personRepository.findAll());
    }
}