package com.barrettotte.asteroid.controller;

import com.barrettotte.asteroid.model.Asteroid;

import com.barrettotte.asteroid.service.AsteroidService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1/asteroids")
public class AsteroidController {

    @Autowired
    AsteroidService asteroidService;

    private final static Logger LOGGER = LoggerFactory.getLogger(AsteroidController.class);

    @GetMapping(value = "/{id}", produces = "application/json")
    public ResponseEntity<Asteroid> getAsteroidById(@PathVariable(value = "id") String id) {
        try {
            Optional<Asteroid> asteroid = asteroidService.getById(id);
            return asteroid.map(value -> new ResponseEntity<>(value, HttpStatus.OK)).orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
        } catch (Exception e) {
            LOGGER.error("Failed to fetch asteroid by id.", e);
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping(produces = "application/json")
    public ResponseEntity<List<Asteroid>> getAsteroids() {
        try {
            List<Asteroid> asteroids = asteroidService.getAll();
            if (asteroids.size() == 0) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
            return new ResponseEntity<>(asteroids, HttpStatus.OK);
        } catch (Exception e) {
            LOGGER.error("Failed to fetch asteroids.", e);
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping(produces = "application/json", consumes = "application/json")
    public ResponseEntity<Asteroid> createAsteroid(@RequestBody Asteroid toCreate) {
        try {
            Asteroid created = asteroidService.create(toCreate);
            return ResponseEntity.ok(created);
        } catch (Exception e) {
            LOGGER.error("Failed to create asteroid.", e);
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping(value = "/{id}", produces = "application/json", consumes = "application/json")
    public ResponseEntity<Asteroid> updateAsteroid(@PathVariable(value = "id") String id, @RequestBody Asteroid toUpdate) {
        try {
            if (asteroidService.getById(id).isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
            Asteroid updated = asteroidService.update(toUpdate);
            return new ResponseEntity<>(updated, HttpStatus.OK);
        } catch (Exception e) {
            LOGGER.error("Failed to update asteroid.", e);
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<String> deleteAsteroid(@PathVariable(value = "id") String id) {
        try {
            if (asteroidService.getById(id).isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
            String deleted = asteroidService.delete(id);
            return new ResponseEntity<>(deleted, HttpStatus.OK);
        } catch (Exception e) {
            LOGGER.error("Failed to delete asteroid.", e);
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
