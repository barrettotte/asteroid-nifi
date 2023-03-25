package com.barrettotte.asteroid.controller;

import com.barrettotte.asteroid.model.Asteroid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("api/v1/asteroids")
public class AsteroidController {

    private final static Logger LOGGER = LoggerFactory.getLogger(AsteroidController.class);

    @GetMapping(value = "/{id}", produces = "application/json")
    public ResponseEntity<Asteroid> getAsteroidById(@PathVariable(value = "id") Long id) {
        try {
            Asteroid asteroid = new Asteroid(); // TODO:
            return ResponseEntity.ok(asteroid);
        } catch (Exception e) {
            LOGGER.error("Failed to fetch asteroid by id.", e);
            return ResponseEntity.internalServerError().build();
        }
    }

    @GetMapping(produces = "application/json")
    public ResponseEntity<List<Asteroid>> getAsteroids() {
        try {
            List<Asteroid> asteroids = new ArrayList<>(); // TODO:
            return ResponseEntity.ok(asteroids);
        } catch (Exception e) {
            LOGGER.error("Failed to fetch asteroids.", e);
            return ResponseEntity.internalServerError().build();
        }
    }

    @PostMapping(produces = "application/json", consumes = "application/json")
    public ResponseEntity<Asteroid> createAsteroid() {
        try {
            Asteroid asteroid = new Asteroid(); // TODO:
            return ResponseEntity.ok(asteroid);
        } catch (Exception e) {
            LOGGER.error("Failed to create asteroid.", e);
            return ResponseEntity.internalServerError().build();
        }
    }

    @PutMapping(value = "/{id}", produces = "application/json", consumes = "application/json")
    public ResponseEntity<Asteroid> updateAsteroid(@PathVariable(value = "id") Long id) {
        try {
            Asteroid asteroid = new Asteroid(); // TODO:
            return ResponseEntity.ok(asteroid);
        } catch (Exception e) {
            LOGGER.error("Failed to update asteroid.", e);
            return ResponseEntity.internalServerError().build();
        }
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Long> deleteAsteroid(@PathVariable(value = "id") Long id) {
        try {
            return ResponseEntity.ok(id); // TODO:
        } catch (Exception e) {
            LOGGER.error("Failed to delete asteroid.", e);
            return ResponseEntity.internalServerError().build();
        }
    }
}
